package com.algaworks.junit.utilidade;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.util.Arrays;
import java.util.List;

class FiltroNumerosTest {
    @Test
    @DisplayName("Testando retorno de números pares")
    public void deveRetornanarNumeroPar() {
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4);
        List<Integer> numerosParesEsperados = Arrays.asList(2, 4);
        List<Integer> resultado = FiltroNumeros.numerosPares(numeros);
        assertIterableEquals(numerosParesEsperados, resultado);
    }
}