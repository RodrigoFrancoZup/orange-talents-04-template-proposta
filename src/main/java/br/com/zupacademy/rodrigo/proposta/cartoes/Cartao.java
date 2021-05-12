package br.com.zupacademy.rodrigo.proposta.cartoes;

import br.com.zupacademy.rodrigo.proposta.propostas.Proposta;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
public class Cartao {

    @Id
    private String id;
    private String titular;
    private LocalDateTime criadoEm;

    @OneToOne(mappedBy = "cartao")
    private Proposta proposta;

    @Deprecated
    public Cartao() {
    }

    public Cartao(String id, String titular, LocalDateTime criadoEm, Proposta proposta) {
        this.id = id;
        this.titular = titular;
        this.criadoEm = criadoEm;
        this.proposta = proposta;
    }

    public String getId() {
        return id;
    }

    public String getTitular() {
        return titular;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public Proposta getProposta() {
        return proposta;
    }
}
