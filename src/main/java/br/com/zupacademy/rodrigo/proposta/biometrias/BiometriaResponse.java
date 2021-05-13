package br.com.zupacademy.rodrigo.proposta.biometrias;

import java.time.LocalDateTime;

public class BiometriaResponse {

    private Long id;
    private String titular;
    private LocalDateTime instanteCriacao;
    private String fingerprint;

    public BiometriaResponse(Biometria biometria) {
        this.id = biometria.getId();
        this.titular = biometria.getCartao().getTitular();
        this.instanteCriacao = biometria.getInstanteCriacao();
        this.fingerprint = biometria.getFingerprint();
    }

    public Long getId() {
        return id;
    }

    public String getTitular() {
        return titular;
    }

    public LocalDateTime getInstanteCriacao() {
        return instanteCriacao;
    }

    public String getFingerprint() {
        return fingerprint;
    }
}
