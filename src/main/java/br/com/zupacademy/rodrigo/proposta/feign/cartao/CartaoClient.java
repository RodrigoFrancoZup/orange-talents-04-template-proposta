package br.com.zupacademy.rodrigo.proposta.feign.cartao;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "cartoes", url = "${cartoes.host}")
public interface CartaoClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/cartoes")
    public CartaoResponseConsultaExterna buscaCartao(@RequestParam(value = "idProposta") Long id);

    @PostMapping("/api/cartoes/{id}/bloqueios")
    public BloqueioCartaoResponseExterno bloqueiaCartao(@PathVariable String id, BloqueioCartaoRequestExterno bloqueioCartaoRequestExterno);

    @PostMapping("/api/cartoes/{id}/avisos")
    public ViagemResponseExterno avisaViagem(@PathVariable String id, ViagemRequestExterno viagemRequestExterno);

    @PostMapping("/api/cartoes/{id}/carteiras")
    public CarteiraResponseExterno  associaCartaoCarteira(@PathVariable String id, CarteiraRequestExterno  carteiraRequestExterno);
}
