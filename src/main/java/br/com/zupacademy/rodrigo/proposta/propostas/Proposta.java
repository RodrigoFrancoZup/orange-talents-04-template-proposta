package br.com.zupacademy.rodrigo.proposta.propostas;

import br.com.zupacademy.rodrigo.proposta.cartoes.Cartao;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Proposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String documento;
    private String email;
    private String nome;
    private String endereco;
    private BigDecimal salario;

    @OneToOne(cascade = CascadeType.ALL)
    private Cartao cartao;


    @Enumerated(EnumType.STRING)
    private StatusProposta status = StatusProposta.EM_ANALISE;

    @Deprecated
    public Proposta() {
    }

    public Proposta(String documento, String email, String nome, String endereco, BigDecimal salario) {
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
        criptografiaDoDocumento();
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

    public Cartao getCartao() {
        return cartao;
    }

    public void setStatus(StatusProposta status) {
        this.status = status;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }


    public void criptografiaDoDocumento(){
        TextEncryptor encryptor = Encryptors.queryableText("senha123", "5c0744940b5c369c");
        String documentoProtegido = encryptor.encrypt(this.documento);
        this.documento = documentoProtegido;
    }
}
