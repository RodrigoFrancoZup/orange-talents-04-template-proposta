package br.com.zupacademy.rodrigo.proposta.propostas;

import br.com.zupacademy.rodrigo.proposta.feign.solicitacao.AnaliseSolicitacaoRequest;
import br.com.zupacademy.rodrigo.proposta.feign.solicitacao.AnaliseSolicitacaoResponse;
import br.com.zupacademy.rodrigo.proposta.feign.solicitacao.SistemaDeDadosFinanceirosClient;
import br.com.zupacademy.rodrigo.proposta.spring.metricas.MinhasMetricas;
import feign.FeignException;

import io.opentracing.Span;
import io.opentracing.Tracer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/proposta")
public class PropostaController {

    private final Logger logger = LoggerFactory.getLogger(PropostaController.class);

    private final Tracer tracer;

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private SistemaDeDadosFinanceirosClient sistemaDeDadosFinanceirosClient;

    @Autowired
    private MinhasMetricas minhasMetricas;

    public PropostaController(Tracer tracer) {
        this.tracer = tracer;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastra(@RequestBody @Valid PropostaRequest propostaRequest, UriComponentsBuilder uriBuilder) {
        Span activeSpan = tracer.activeSpan();
        activeSpan.setTag("user.email", propostaRequest.getEmail());
        activeSpan.setBaggageItem("user.email", propostaRequest.getEmail());
        activeSpan.log("Meu log personalizado - Rodrigo");

        Proposta proposta = propostaRequest.convertePropostaRequestParaProposta();
        Optional<Proposta> possivelProposta = propostaRepository.findByDocumento(proposta.getDocumento());
        if (possivelProposta.isPresent()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        Proposta propostaSalva = propostaRepository.save(proposta);
        logger.info("Proposta documento={} salário={} criada com sucesso!", propostaSalva.getDocumento(), propostaSalva.getSalario());

        AnaliseSolicitacaoRequest analiseSolicitacaoRequest = new AnaliseSolicitacaoRequest(propostaSalva);

        try {
            AnaliseSolicitacaoResponse analiseSolicitacaoResponse = sistemaDeDadosFinanceirosClient.status(analiseSolicitacaoRequest);
            if (analiseSolicitacaoResponse.getResultadoSolicitacao().equals("SEM_RESTRICAO")) {
                propostaSalva.setStatus(StatusProposta.ELEGIVEL);
            }
        } catch (FeignException e) {
            propostaSalva.setStatus(StatusProposta.NAO_ELEGIVEL);
        }


        Proposta propostaComStatus = propostaRepository.save(propostaSalva);
        minhasMetricas.meuContador();
        URI uri = uriBuilder.path("/proposta/{id}").buildAndExpand(proposta.getId()).toUri();
        return ResponseEntity.created(uri).body(new PropostaResponse(propostaComStatus));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalhe(@PathVariable Long id) {
        minhasMetricas.meuTimer();
        Optional<Proposta> propsotaPossivel = propostaRepository.findById(id);
        if (propsotaPossivel.isPresent()) {
            return ResponseEntity.ok(new PropostaResponse(propsotaPossivel.get()));
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
