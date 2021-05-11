package br.com.zupacademy.rodrigo.proposta.feign.cartao;

public class CartaoResponse {

    private String id;

    public CartaoResponse(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public CartaoResponse() {
    }
}
