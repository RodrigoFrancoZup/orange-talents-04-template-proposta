package br.com.zupacademy.rodrigo.proposta.feign.solicitacao;

import br.com.zupacademy.rodrigo.proposta.propostas.Proposta;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

public class AnaliseSolicitacaoRequest {

    private String documento;
    private String nome;
    private Long idProposta;


    public AnaliseSolicitacaoRequest(Proposta proposta) {
        this.documento = proposta.getDocumento();
        this.nome = proposta.getNome();
        this.idProposta = proposta.getId();
        descriptografiaDocumento();
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public Long getIdProposta() {
        return idProposta;
    }

    public void descriptografiaDocumento() {
        TextEncryptor encryptor = Encryptors.queryableText("senha123", "5c0744940b5c369c");
        String documentoDesprotegido = encryptor.decrypt(this.documento);
        this.documento = documentoDesprotegido;
    }
}
