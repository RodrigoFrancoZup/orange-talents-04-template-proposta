package br.com.zupacademy.rodrigo.proposta.carteira;

import br.com.zupacademy.rodrigo.proposta.cartoes.Cartao;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

public class CarteiraResponse {

    private Long id;
    private String email;
    private String UUID;
    private TipoCarteira carteira;
    private String cartao;

    public CarteiraResponse(Carteira carteira) {
        this.id = carteira.getId();
        this.email = carteira.getEmail();
        this.UUID = carteira.getUUID();
        this.carteira = carteira.getCarteira();
        this.cartao = carteira.getCartao().getId();
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUUID() {
        return UUID;
    }

    public TipoCarteira getCarteira() {
        return carteira;
    }

    public String getCartao() {
        return cartao;
    }
}
