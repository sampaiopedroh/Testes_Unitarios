package com.algaworks.junit.utilidade;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class FiltroNumerosTest {
    @Test
    public void deveRetornarNumerosPares() {
        List<Integer> numeros = Arrays.asList(1, 2, 3, 4);
        // A ordem é importante
        List<Integer> numerosEsperados = Arrays.asList(2, 4);

        List<Integer> numerosPares = FiltroNumeros.numerosPares(numeros);

        // Para listas
        assertIterableEquals(numerosEsperados, numerosPares);

        // Para arrays
        // assertArrayEquals(numerosEsperados.toArray(new Object[]{}), numerosEsperados.toArray(new Object[]{}));
    }
}