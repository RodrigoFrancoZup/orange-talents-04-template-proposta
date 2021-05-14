package br.com.zupacademy.rodrigo.proposta.feign.cartao;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BloqueioCartaoResponseExterno {

    private String resultado;

    public BloqueioCartaoResponseExterno(@JsonProperty("resultado") String resultado) {
        this.resultado = resultado;
    }

    public String getResultado() {
        return resultado;
    }


}
