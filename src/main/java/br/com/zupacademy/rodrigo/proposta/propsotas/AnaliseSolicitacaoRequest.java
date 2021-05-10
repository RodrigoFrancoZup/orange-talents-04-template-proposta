package br.com.zupacademy.rodrigo.proposta.propsotas;

public class AnaliseSolicitacaoRequest {


    private String documento;
    private String nome;
    private Long idProposta;


    public AnaliseSolicitacaoRequest(Proposta proposta) {
        this.documento = proposta.getDocumento();
        this.nome = proposta.getNome();
        this.idProposta = proposta.getId();
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
}
