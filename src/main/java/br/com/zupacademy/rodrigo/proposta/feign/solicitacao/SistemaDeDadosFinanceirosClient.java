package br.com.zupacademy.rodrigo.proposta.feign.solicitacao;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "sistemaDeDadosFinanceiros", url = "${financeiro.host}")
public interface SistemaDeDadosFinanceirosClient {

    /*
    No lugar do @RequestMapping poderiamos usar o @Postmapping("/api/solicitacao")
     */
    @RequestMapping(method = RequestMethod.POST, value = "/api/solicitacao")
    @Headers("Content-Type: application/json")
    public AnaliseSolicitacaoResponse status(AnaliseSolicitacaoRequest analiseSolicitacaoRequest);
}
