package com.algaworks.junit.utilidade;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

class SimuladorEsperaTest {
    @Test
    // Outra maneira de desabilitar o teste por uma env
    @EnabledIfEnvironmentVariable(named = "ENV", matches = "PROD")
    public void deveEsperarENaoDarTimeout() {
        // Para desabilitar um teste por uma variável de ambiente
        // Assumptions.assumeTrue("PROD".equals(System.getenv("ENV")), () -> "Abortando teste em prod");

        // Lança falha depois do método rodar
        // assertTimeout(Duration.ofSeconds(1), () -> SimuladorEspera.esperar(Duration.ofSeconds(10)));

        // Lança falha assim que passar o tempo estimado
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> SimuladorEspera.esperar(Duration.ofSeconds(10)));
    }

}