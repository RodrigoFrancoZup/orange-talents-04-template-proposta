package br.com.zupacademy.rodrigo.proposta.feign.cartao;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "cartoes", url = "http://localhost:8888")
public interface CartaoClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/cartoes")
    public CartaoResponse buscaCartao(@RequestParam(value = "idProposta") Long id );
}
