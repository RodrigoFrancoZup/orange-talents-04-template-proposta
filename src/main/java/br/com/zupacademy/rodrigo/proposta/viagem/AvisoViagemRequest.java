package br.com.zupacademy.rodrigo.proposta.viagem;

import br.com.zupacademy.rodrigo.proposta.cartoes.Cartao;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AvisoViagemRequest {

    @NotBlank
    private String destinoViagem;

    @NotNull
    @Future
    private LocalDate dataTermino;

    private String ipClient;

    private String userAgent;

    public AvisoViagemRequest(String destinoViagem, LocalDate dataTermino, String ipClient, String userAgent) {
        this.destinoViagem = destinoViagem;
        this.dataTermino = dataTermino;

    }

    public AvisoViagem converteViagemRequestParaAvisoViagem(Cartao cartao){
        return new AvisoViagem(this.destinoViagem, this.dataTermino, this.ipClient, this.userAgent, cartao);
    }

    public String getDestinoViagem() {
        return destinoViagem;
    }

    public LocalDate getDataTermino() {
        return dataTermino;
    }

    public String getIpClient() {
        return ipClient;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setIpClient(String ipClient) {
        this.ipClient = ipClient;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }
}
