package br.com.zupacademy.rodrigo.proposta.feign.cartao;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ViagemResponseExterno {

    private  String resultado;

    public ViagemResponseExterno(@JsonProperty("resultado") String resultado) {
        this.resultado = resultado;
    }

    public String getResultado() {
        return resultado;
    }
}
