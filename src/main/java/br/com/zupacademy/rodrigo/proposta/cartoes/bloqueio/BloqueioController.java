package br.com.zupacademy.rodrigo.proposta.cartoes.bloqueio;

import br.com.zupacademy.rodrigo.proposta.cartoes.Cartao;
import br.com.zupacademy.rodrigo.proposta.cartoes.CartaoRepository;
import br.com.zupacademy.rodrigo.proposta.cartoes.StatusCartao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/bloqueio")
public class BloqueioController {

    @Autowired
    private BloqueioRepository bloqueioRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

    @PostMapping("/{idCartao}")
    public ResponseEntity<?> bloqueia(@RequestHeader(value = "User-Agent") String userAgent, @RequestHeader(value = "Host") String ipClient, @PathVariable String idCartao, UriComponentsBuilder uriBuilder) {
        BloqueioRequest bloqueioRequest = new BloqueioRequest(ipClient, userAgent);
        Optional<Cartao> cartaoPossivel = cartaoRepository.findById(idCartao);
        if (cartaoPossivel.isPresent()) {
            if (cartaoPossivel.get().getStatus() == StatusCartao.BLOQUEADO) {
                return ResponseEntity.unprocessableEntity().build();
            } else {
                Bloqueio bloqueio = bloqueioRequest.converteBloqueioRequestParaBloqueio(cartaoPossivel.get());
                bloqueioRepository.save(bloqueio);
                cartaoPossivel.get().bloqueiaCartao();
                cartaoRepository.save(cartaoPossivel.get());

                URI uri = uriBuilder.path("/bloqueio/{id}").buildAndExpand(bloqueio.getId()).toUri();
                return ResponseEntity.created(uri).body(new BloqueioResponse(bloqueio));

            }
        }
        return ResponseEntity.notFound().build();
    }
}
