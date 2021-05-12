package br.com.zupacademy.rodrigo.proposta.propostas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PropostaRepository extends JpaRepository<Proposta, Long> {

    public Optional<Proposta> findByDocumento(String documento);

    @Query("SELECT p from Proposta p WHERE p.status = 'ELEGIVEL' AND p.cartao.id = NULL")
    public List<Proposta> buscaPropostasElegiveisSemCartao();

}