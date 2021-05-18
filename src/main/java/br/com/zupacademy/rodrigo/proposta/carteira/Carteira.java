package br.com.zupacademy.rodrigo.proposta.carteira;

import br.com.zupacademy.rodrigo.proposta.cartoes.Cartao;

import javax.persistence.*;

@Entity
public class Carteira {

    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String UUID;

    @Enumerated(EnumType.STRING)
    private TipoCarteira carteira;

    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Carteira() {
    }

    public Carteira(String email, TipoCarteira carteira, Cartao cartao, String UUID) {
        this.email = email;
        this.carteira = carteira;
        this.cartao = cartao;
        this.UUID = UUID;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public TipoCarteira getCarteira() {
        return carteira;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public String getUUID() {
        return UUID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Carteira carteira1 = (Carteira) o;

        return carteira == carteira1.carteira;
    }

    @Override
    public int hashCode() {
        return carteira != null ? carteira.hashCode() : 0;
    }
}
