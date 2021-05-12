package br.com.zupacademy.rodrigo.proposta.feign.cartao;

import br.com.zupacademy.rodrigo.proposta.cartoes.Cartao;
import br.com.zupacademy.rodrigo.proposta.propostas.Proposta;

import java.time.LocalDateTime;

public class CartaoResponseConsultaExterna {

    private String id;
    private String titular;
    private LocalDateTime emitidoEm;

    @Deprecated
    public CartaoResponseConsultaExterna() {
    }

    public CartaoResponseConsultaExterna(String id, String titular, LocalDateTime emitidoEm) {
        this.id = id;
        this.titular = titular;
        this.emitidoEm = emitidoEm;
    }

    public Cartao converteParaCartao(Proposta proposta) {
        return new Cartao(this.id, this.titular, this.emitidoEm, proposta);
    }

    public String getId() {
        return id;
    }

    public String getTitular() {
        return titular;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }
}
