package br.com.zupacademy.rodrigo.proposta.biometrias;

import br.com.zupacademy.rodrigo.proposta.cartoes.Cartao;
import br.com.zupacademy.rodrigo.proposta.cartoes.CartaoRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/biometria")
public class BiometriaController {

    @Autowired
    private BiometriaRepository biometriaRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

    @PostMapping("/{cartaoId}")
    @Transactional
    public ResponseEntity<?> cadastra(@PathVariable String cartaoId, @RequestBody @Valid BiometriaRequest biometriaRequest, UriComponentsBuilder uriBuilder) {
        Optional<Cartao> cartaoPossivel = cartaoRepository.findById(cartaoId);
        if (cartaoPossivel.isPresent()) {
            if (Base64.isBase64(biometriaRequest.getFingerprint())) {
                Biometria biometria = biometriaRequest.converteBiometriaRequestParaBiometria(cartaoPossivel.get());
                biometriaRepository.save(biometria);
                URI uri = uriBuilder.path("/biometria/{id}").buildAndExpand(biometria.getId()).toUri();
                return ResponseEntity.created(uri).body(new BiometriaResponse(biometria));
            } else {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
