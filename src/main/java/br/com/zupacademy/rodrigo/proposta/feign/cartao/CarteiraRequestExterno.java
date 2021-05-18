package br.com.zupacademy.rodrigo.proposta.feign.cartao;

import br.com.zupacademy.rodrigo.proposta.carteira.CarteiraRequest;
import br.com.zupacademy.rodrigo.proposta.carteira.TipoCarteira;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CarteiraRequestExterno {

    @NotBlank
    @Email
    private String email;

    @NotNull
    private TipoCarteira carteira;

    public CarteiraRequestExterno(String email, TipoCarteira carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public CarteiraRequestExterno(CarteiraRequest carteiraRequest){
        this.email = carteiraRequest.getEmail();
        this.carteira = carteiraRequest.getCarteira();
    }

    public String getEmail() {
        return email;
    }

    public TipoCarteira getCarteira() {
        return carteira;
    }
}
