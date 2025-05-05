package com.algaworks.junit.utilidade;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.function.Executable;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class SimuladorEsperaTest {
    @Test
    // Como desabilitar um teste
    // @Disabled("Desabilitando um teste")

    // Desabilita o teste se a variável de ambiente não tiver o valor desejável
    @EnabledIfEnvironmentVariable(named = "ENV", matches = "DEV")
    public void testesComTimeout() {
        // Desabilita o teste por uma variável de ambiente (usando assumeTrue)
        //assumeTrue("PROD".equals(System.getenv("ENV")), () -> "Desabilitado em prod");

        Executable executable = () -> SimuladorEspera.esperar(Duration.ofMillis(10));

        // Deixa o teste continuar e depois lança o erro
        // assertTimeout(Duration.ofSeconds(1), executable);

        // Assim que passa o tempo, para a execução e lança o erro
        assertTimeoutPreemptively(Duration.ofSeconds(1), executable);
    }
}
