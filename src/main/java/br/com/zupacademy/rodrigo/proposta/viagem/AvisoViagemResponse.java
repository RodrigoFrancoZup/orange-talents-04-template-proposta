package br.com.zupacademy.rodrigo.proposta.viagem;

import br.com.zupacademy.rodrigo.proposta.cartoes.Cartao;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AvisoViagemResponse {

    private Long id;
    private String destinoViagem;
    private LocalDate dataTermino;
    private String ipClient;
    private String userAgent;
    private LocalDateTime instanteCriacao;
    private String cartao;

    public AvisoViagemResponse(AvisoViagem avisoViagem) {
        this.id = avisoViagem.getId();
        this.destinoViagem = avisoViagem.getDestinoViagem();
        this.dataTermino = avisoViagem.getDataTermino();
        this.ipClient = avisoViagem.getIpClient();
        this.userAgent = avisoViagem.getUserAgent();
        this.instanteCriacao = avisoViagem.getInstanteCriacao();
        this.cartao = avisoViagem.getCartao().getId();
    }

    public Long getId() {
        return id;
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

    public LocalDateTime getInstanteCriacao() {
        return instanteCriacao;
    }

    public String getCartao() {
        return cartao;
    }
}
