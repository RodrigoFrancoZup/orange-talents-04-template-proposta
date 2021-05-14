package br.com.zupacademy.rodrigo.proposta.cartoes.bloqueio;

import br.com.zupacademy.rodrigo.proposta.cartoes.Cartao;

import java.time.LocalDateTime;

public class BloqueioRequest {
    private String ipClient;
    private String userAgent;

    public BloqueioRequest(String ipClient, String userAgent) {
        this.ipClient = ipClient;
        this.userAgent = userAgent;
    }

    public Bloqueio converteBloqueioRequestParaBloqueio(Cartao cartao) {
        return new Bloqueio(this.ipClient, this.userAgent, cartao);
    }

    public String getIpClient() {
        return ipClient;
    }

    public String getUserAgent() {
        return userAgent;
    }
}
