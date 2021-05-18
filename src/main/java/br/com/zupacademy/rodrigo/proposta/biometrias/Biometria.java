package br.com.zupacademy.rodrigo.proposta.biometrias;

import br.com.zupacademy.rodrigo.proposta.cartoes.Cartao;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Biometria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fingerprint;
    private LocalDateTime instanteCriacao = LocalDateTime.now();

    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Biometria() {
    }

    public Biometria(String fingerprint, Cartao cartao) {
        this.fingerprint = fingerprint;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public LocalDateTime getInstanteCriacao() {
        return instanteCriacao;
    }

    public Cartao getCartao() {
        return cartao;
    }
}
