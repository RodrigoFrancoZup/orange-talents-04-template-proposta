package br.com.zupacademy.rodrigo.proposta.scheduled;

import br.com.zupacademy.rodrigo.proposta.cartoes.Cartao;
import br.com.zupacademy.rodrigo.proposta.feign.cartao.CartaoClient;
import br.com.zupacademy.rodrigo.proposta.feign.cartao.CartaoResponseConsultaExterna;
import br.com.zupacademy.rodrigo.proposta.propostas.Proposta;
import br.com.zupacademy.rodrigo.proposta.propostas.PropostaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PropostaCartao {

    private final Logger logger = LoggerFactory.getLogger(PropostaCartao.class);

    @Autowired
    private PropostaRepository propostaRepository;

    @Autowired
    private CartaoClient cartaoClient;

    @Scheduled(fixedDelay = 10000)
    public void buscaCartaoParaCadaProposta() {
        List<Proposta> propostasSemCartao = propostaRepository.buscaPropostasElegiveisSemCartao();
        for (Proposta proposta : propostasSemCartao) {
            try {
                CartaoResponseConsultaExterna cartaoResponseConsultaExterna = cartaoClient.buscaCartao(proposta.getId());
                logger.info("A proposta {} recebeu o cartão {}", proposta.getId(), cartaoResponseConsultaExterna.getId());
                Cartao cartao = cartaoResponseConsultaExterna.converteParaCartao(proposta);
                proposta.setCartao(cartao);
                propostaRepository.save(proposta);
            } catch (Exception e) {
                logger.info("A proposta {} não recebeu o cartão", proposta.getId());
            }
        }

    }

}

