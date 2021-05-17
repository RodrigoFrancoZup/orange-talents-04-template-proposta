package br.com.zupacademy.rodrigo.proposta.viagem;

import br.com.zupacademy.rodrigo.proposta.cartoes.Cartao;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class AvisoViagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String destinoViagem;
    private LocalDate dataTermino;
    private String ipClient;
    private String userAgent;
    private LocalDateTime instanteCriacao = LocalDateTime.now();

    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public AvisoViagem() {
    }

    public AvisoViagem(String destinoViagem, LocalDate dataTermino, String ipClient, String userAgent, Cartao cartao) {
        this.destinoViagem = destinoViagem;
        this.dataTermino = dataTermino;
        this.ipClient = ipClient;
        this.userAgent = userAgent;
        this.cartao = cartao;
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

    public Cartao getCartao() {
        return cartao;
    }
}
