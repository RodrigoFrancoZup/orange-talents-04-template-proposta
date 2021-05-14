package br.com.zupacademy.rodrigo.proposta.cartoes.bloqueio;

import java.time.LocalDateTime;

public class BloqueioResponse {
    private Long id;
    private LocalDateTime instanteDoBloqueio = LocalDateTime.now();
    private String ipClient;
    private String userAgent;
    private String numeroCartaoBloqueado;

    public BloqueioResponse(Bloqueio bloqueio) {
        this.id = bloqueio.getId();
        this.instanteDoBloqueio = bloqueio.getInstanteDoBloqueio();
        this.ipClient = bloqueio.getIpClient();
        this.userAgent = bloqueio.getUserAgent();
        this.numeroCartaoBloqueado = bloqueio.getCartao().getId();
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getInstanteDoBloqueio() {
        return instanteDoBloqueio;
    }

    public String getIpClient() {
        return ipClient;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public String getNumeroCartaoBloqueado() {
        return numeroCartaoBloqueado;
    }
}
