package br.com.zupacademy.rodrigo.proposta.carteira;

import br.com.zupacademy.rodrigo.proposta.cartoes.Cartao;
import br.com.zupacademy.rodrigo.proposta.feign.cartao.CarteiraResponseExterno;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CarteiraRequest {

    @NotBlank
    @Email
    private String email;

    @NotNull
    private TipoCarteira carteira;

    public CarteiraRequest(String email, TipoCarteira carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getEmail() {
        return email;
    }

    public TipoCarteira getCarteira() {
        return carteira;
    }

    public Carteira converteCarteiraRequestParaCarteira(CarteiraResponseExterno carteiraResponseExterno, Cartao cartao){
        return new Carteira(this.email, this.carteira, cartao, carteiraResponseExterno.getId());
    }
}
