package br.com.zupacademy.rodrigo.proposta.cartoes.bloqueio;

import br.com.zupacademy.rodrigo.proposta.cartoes.Cartao;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Bloqueio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime instanteDoBloqueio = LocalDateTime.now();
    private String ipClient;
    private String userAgent;

    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Bloqueio() {
    }

    public Bloqueio(String ipClient, String userAgent, Cartao cartao) {
        this.ipClient = ipClient;
        this.userAgent = userAgent;
        this.cartao = cartao;
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

    public Cartao getCartao() {
        return cartao;
    }
}
