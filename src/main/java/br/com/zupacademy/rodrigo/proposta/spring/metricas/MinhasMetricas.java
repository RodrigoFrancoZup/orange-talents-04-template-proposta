package br.com.zupacademy.rodrigo.proposta.spring.metricas;

import br.com.zupacademy.rodrigo.proposta.propostas.PropostaController;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class MinhasMetricas {

    private final MeterRegistry meterRegistry;

    public MinhasMetricas(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    public void meuContador() {
        Collection<Tag> tags = new ArrayList<>();
        tags.add(Tag.of("emissora", "Mastercard"));
        tags.add(Tag.of("banco", "Itaú"));

        Counter contadorDePropostasCriadas = this.meterRegistry.counter("proposta_criada", tags);
        contadorDePropostasCriadas.increment();
    }

    public void meuTimer(){
        Collection<Tag> tags = new ArrayList<>();
        tags.add(Tag.of("emissora", "Mastercard"));
        tags.add(Tag.of("banco", "Itaú"));

        Timer timerConsultarProposta = this.meterRegistry.timer("consultar_proposta", tags);
        timerConsultarProposta.record(() -> {
            // Método da sua operação
            detalheProposta();
        });
    }

    private void detalheProposta() {
    }

}
