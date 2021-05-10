package br.com.zupacademy.rodrigo.proposta.propsotas;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "sistemaDeDadosFinanceiros", url = "http://localhost:9999")
public interface SistemaDeDadosFinanceirosClient {

    @RequestMapping(method = RequestMethod.POST, value = "/api/solicitacao")
    @Headers("Content-Type: application/json")
    public AnaliseSolicitacaoResponse status(AnaliseSolicitacaoRequest analiseSolicitacaoRequest);
}
