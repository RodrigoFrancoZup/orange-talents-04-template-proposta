package br.com.zupacademy.rodrigo.proposta.feign.cartao;

import java.time.LocalDate;

public class ViagemRequestExterno {

    private String destino;
    private LocalDate validoAte;

    public ViagemRequestExterno(String destino, LocalDate validoAte) {
        this.destino = destino;
        this.validoAte = validoAte;
    }

    public String getDestino() {
        return destino;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }
}
