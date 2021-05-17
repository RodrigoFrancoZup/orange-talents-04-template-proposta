package br.com.zupacademy.rodrigo.proposta.viagem;

import br.com.zupacademy.rodrigo.proposta.cartoes.Cartao;
import br.com.zupacademy.rodrigo.proposta.cartoes.CartaoRepository;
import br.com.zupacademy.rodrigo.proposta.feign.cartao.CartaoClient;
import br.com.zupacademy.rodrigo.proposta.feign.cartao.ViagemRequestExterno;
import br.com.zupacademy.rodrigo.proposta.feign.cartao.ViagemResponseExterno;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

@RestController
@RequestMapping("/aviso-viagem")
public class AvisoViagemController {

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private AvisoViagemRepository avisoViagemRepository;

    @Autowired
    private CartaoClient cartaoClient;

    @PostMapping("/{idCartao}")
    public ResponseEntity<?> cadastra(@RequestBody @Valid AvisoViagemRequest avisoViagemRequest, @PathVariable String idCartao, @RequestHeader(value = "User-Agent") @NotBlank String userAgent, @RequestHeader(value = "Host") @NotBlank String ipClient) {
        Optional<Cartao> cartaoPossivel = cartaoRepository.findById(idCartao);
        if (cartaoPossivel.isPresent()) {
            try {
                ViagemRequestExterno requestExterno = new ViagemRequestExterno(avisoViagemRequest.getDestinoViagem(), avisoViagemRequest.getDataTermino());
                ViagemResponseExterno responseExterno = cartaoClient.avisaViagem(idCartao, requestExterno);
                if (responseExterno.getResultado().equals("CRIADO")) {
                    avisoViagemRequest.setIpClient(ipClient);
                    avisoViagemRequest.setUserAgent(userAgent);
                    AvisoViagem avisoViagem = avisoViagemRequest.converteViagemRequestParaAvisoViagem(cartaoPossivel.get());
                    avisoViagemRepository.save(avisoViagem);
                    return ResponseEntity.ok(new AvisoViagemResponse(avisoViagem));
                }
            } catch (FeignException e) {
                return ResponseEntity.badRequest().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

}
