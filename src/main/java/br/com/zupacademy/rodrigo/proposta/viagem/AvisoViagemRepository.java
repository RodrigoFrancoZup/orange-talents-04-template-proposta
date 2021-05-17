package br.com.zupacademy.rodrigo.proposta.viagem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvisoViagemRepository extends JpaRepository<AvisoViagem, Long> {
}
