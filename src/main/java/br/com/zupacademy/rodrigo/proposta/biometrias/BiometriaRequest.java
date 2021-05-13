package br.com.zupacademy.rodrigo.proposta.biometrias;

import br.com.zupacademy.rodrigo.proposta.cartoes.Cartao;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;


public class BiometriaRequest {

    @NotBlank
    private String fingerprint;


    public BiometriaRequest(@JsonProperty(value = "fingerprint") String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public Biometria converteBiometriaRequestParaBiometria(Cartao cartao){
        return new Biometria(this.fingerprint, cartao);
    }
}
