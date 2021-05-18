package br.com.zupacademy.rodrigo.proposta.cartoes;

import br.com.zupacademy.rodrigo.proposta.biometrias.Biometria;
import br.com.zupacademy.rodrigo.proposta.carteira.Carteira;
import br.com.zupacademy.rodrigo.proposta.carteira.TipoCarteira;
import br.com.zupacademy.rodrigo.proposta.propostas.Proposta;
import br.com.zupacademy.rodrigo.proposta.viagem.AvisoViagem;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Cartao {

    @Id
    private String id;
    private String titular;
    private LocalDateTime criadoEm;

    @Enumerated(EnumType.STRING)
    private StatusCartao status;

    @OneToOne(mappedBy = "cartao")
    private Proposta proposta;

    @OneToMany(mappedBy = "cartao")
    private List<Biometria> biometrias;

    @OneToMany(mappedBy = "cartao")
    private List<AvisoViagem> avisosDeViagem;

    @OneToMany(mappedBy = "cartao")
    private List<Carteira> carteiras;

    @Deprecated
    public Cartao() {
    }

    public Cartao(String id, String titular, LocalDateTime criadoEm, Proposta proposta) {
        this.id = id;
        this.titular = titular;
        this.criadoEm = criadoEm;
        this.proposta = proposta;
    }

    public void bloqueiaCartao() {
        this.status = StatusCartao.BLOQUEADO;
    }

    public Boolean verificaSeJaEhAssociado(TipoCarteira tipoCarteira){
        for(Carteira aux : carteiras){
            if(aux.getCarteira().equals(tipoCarteira)){
                return true;
            }
        }
        return false;
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

    public StatusCartao getStatus() {
        return status;
    }
}
