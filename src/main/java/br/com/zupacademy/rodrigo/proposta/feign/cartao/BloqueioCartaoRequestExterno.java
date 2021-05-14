package br.com.zupacademy.rodrigo.proposta.feign.cartao;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BloqueioCartaoRequestExterno {

    private String sistemaResponsavel;

    public BloqueioCartaoRequestExterno(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }
}
