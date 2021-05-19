package br.com.zupacademy.rodrigo.proposta.propostas;

import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

import java.math.BigDecimal;

public class PropostaResponse {

    private Long id;
    private String documento;
    private String email;
    private String nome;
    private String endereco;
    private BigDecimal salario;
    private StatusProposta status;
    private String numeroCartao;

    public PropostaResponse(Proposta proposta) {
        this.id = proposta.getId();
        this.documento = proposta.getDocumento();
        this.email = proposta.getEmail();
        this.nome = proposta.getNome();
        this.endereco = proposta.getEndereco();
        this.salario = proposta.getSalario();
        this.status = proposta.getStatus();
        this.numeroCartao = null;
        if (proposta.getCartao() != null) {
            this.numeroCartao = proposta.getCartao().getId();
        }
    }

    public Long getId() {
        return id;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public StatusProposta getStatus() {
        return status;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }


}
