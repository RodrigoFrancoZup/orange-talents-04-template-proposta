package br.com.zupacademy.rodrigo.proposta.carteira;

import br.com.zupacademy.rodrigo.proposta.cartoes.Cartao;
import br.com.zupacademy.rodrigo.proposta.cartoes.CartaoRepository;
import br.com.zupacademy.rodrigo.proposta.feign.cartao.CartaoClient;

import br.com.zupacademy.rodrigo.proposta.feign.cartao.CarteiraRequestExterno;
import br.com.zupacademy.rodrigo.proposta.feign.cartao.CarteiraResponseExterno;

import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/carteira")
public class CarteiraController {

    @Autowired
    private CarteiraRepository carteiraRepository;

    @Autowired
    private CartaoRepository cartaoRepository;

    @Autowired
    private CartaoClient cartaoClient;

    @PostMapping("/{idCartao}")
    public ResponseEntity<?> cadastra(@PathVariable String idCartao, @RequestBody @Valid CarteiraRequest carteiraRequest, UriComponentsBuilder uriBuilder) {
        Optional<Cartao> possivelCartao = cartaoRepository.findById(idCartao);
        if (possivelCartao.isPresent()) {
            if (possivelCartao.get().verificaSeJaEhAssociado(carteiraRequest.getCarteira())) {
                return ResponseEntity.unprocessableEntity().build();
            }
            try {
                CarteiraRequestExterno requestExterno = new CarteiraRequestExterno(carteiraRequest);
                CarteiraResponseExterno carteiraResponseExterno = cartaoClient.associaCartaoCarteira(idCartao, requestExterno);
                if (carteiraResponseExterno.getResultado().equals("ASSOCIADA")) {
                    Carteira carteira = carteiraRequest.converteCarteiraRequestParaCarteira(carteiraResponseExterno, possivelCartao.get());
                    carteiraRepository.save(carteira);
                    URI uri = uriBuilder.path("/carteira/{id}").buildAndExpand(carteira.getId()).toUri();
                    return ResponseEntity.created(uri).body(new CarteiraResponse(carteira));
                }
            } catch (FeignException e) {
                return ResponseEntity.unprocessableEntity().build();
            }
        }
        return ResponseEntity.notFound().build();
    }
}
