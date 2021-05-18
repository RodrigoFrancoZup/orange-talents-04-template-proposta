package br.com.zupacademy.rodrigo.proposta.feign.cartao;

public class CarteiraResponseExterno {

    private String resultado;
    private String id;

    public CarteiraResponseExterno(String resultado, String id) {
        this.resultado = resultado;
        this.id = id;
    }

    public String getResultado() {
        return resultado;
    }

    public String getId() {
        return id;
    }
}
