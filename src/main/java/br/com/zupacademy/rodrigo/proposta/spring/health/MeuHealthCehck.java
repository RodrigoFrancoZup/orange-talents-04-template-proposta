package br.com.zupacademy.rodrigo.proposta.spring.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MeuHealthCehck implements HealthIndicator {
    @Override
    public Health getHealth(boolean includeDetails) {
        return HealthIndicator.super.getHealth(includeDetails);
    }

    @Override
    public Health health() {
        Map<String, Object> details = new HashMap<>();
        details.put("versão", "1.0");
        details.put("descrição", "Meu primeiro Health Check customizado! - API Prosposta.");
        details.put("endereço", "127.0.0.1");
        return Health.status(Status.UP).withDetails(details).build();

    }
}
