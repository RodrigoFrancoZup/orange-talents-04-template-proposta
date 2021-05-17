package br.com.zupacademy.rodrigo.proposta.viagem;

import br.com.zupacademy.rodrigo.proposta.cartoes.Cartao;
import br.com.zupacademy.rodrigo.proposta.cartoes.CartaoRepository;
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

    @PostMapping("/{idCartao}")
    public ResponseEntity<?> cadastra(@RequestBody @Valid AvisoViagemRequest avisoViagemRequest, @PathVariable String idCartao, @RequestHeader(value = "User-Agent") @NotBlank String userAgent, @RequestHeader(value = "Host") @NotBlank String ipClient ){
        Optional<Cartao> cartaoPossivel = cartaoRepository.findById(idCartao);
        if(cartaoPossivel.isPresent()){
            avisoViagemRequest.setIpClient(ipClient);
            avisoViagemRequest.setUserAgent(userAgent);
            AvisoViagem avisoViagem = avisoViagemRequest.converteViagemRequestParaAvisoViagem(cartaoPossivel.get());
            avisoViagemRepository.save(avisoViagem);
            return ResponseEntity.ok(new AvisoViagemResponse(avisoViagem));
        }
        return ResponseEntity.notFound().build();
    }

}
