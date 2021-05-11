package br.com.zupacademy.rodrigo.proposta.feign.solicitacao;

public class AnaliseSolicitacaoResponse {


    private String documento;
    private String nome;
    private String resultadoSolicitacao;
    private Long idProposta;

    public AnaliseSolicitacaoResponse(String documento, String nome, String resultadoSolicitacao, Long idProposta) {
        this.documento = documento;
        this.nome = nome;
        this.resultadoSolicitacao = resultadoSolicitacao;
        this.idProposta = idProposta;
    }


    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getResultadoSolicitacao() {
        return resultadoSolicitacao;
    }

    public Long getIdProposta() {
        return idProposta;
    }
}