package br.com.zupacademy.rodrigo.proposta.propsotas;

import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/proposta")
public class PropostaController {

    private final Logger logger = LoggerFactory.getLogger(PropostaController.class);

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private SistemaDeDadosFinanceirosClient sistemaDeDadosFinanceirosClient;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastra(@RequestBody @Valid PropostaRequest propostaRequest, UriComponentsBuilder uriBuilder) {

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
        URI uri = uriBuilder.path("/proposta/{id}").buildAndExpand(proposta.getId()).toUri();
        return ResponseEntity.created(uri).body(new PropostaResponse(propostaComStatus));
    }
}
