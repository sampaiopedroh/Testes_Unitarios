package com.algaworks.junit.utilidade;


import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ContaBancariaTest {
    @Nested
    class CasosDeSucesso {
        @Test
        public void deveAdicionarSaldo() {
            assertDoesNotThrow(() -> new ContaBancaria(BigDecimal.TEN));
        }

        @Test
        public void deveRealizarSaque() {
            ContaBancaria contaBancaria = new ContaBancaria(BigDecimal.TEN);

            contaBancaria.saque(BigDecimal.ONE);

            assertEquals(new BigDecimal(9), contaBancaria.saldo());
        }

        @Test
        public void deveRealizarDeposito() {
            ContaBancaria contaBancaria = new ContaBancaria(BigDecimal.TEN);

            contaBancaria.deposito(BigDecimal.ONE);

            assertEquals(new BigDecimal(11), contaBancaria.saldo());
        }
    }

    @Nested
    class DeveFalhar {
        @Test
        public void naoDeveAdicionarSaldoNulo() {
            Executable executable = () -> new ContaBancaria(null);
            IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, executable);

            assertEquals("Saldo não pode ser nulo", illegalArgumentException.getMessage());
        }

        @Test
        public void naoDeveRealizarSaqueParaValorNulo() {
            ContaBancaria contaBancaria = new ContaBancaria(BigDecimal.TEN);

            Executable executable = () -> contaBancaria.saque(null);
            IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, executable);

            assertEquals("Valor de saque não pode ser nulo ou menor/igual zero", illegalArgumentException.getMessage());
        }

        @Test
        public void naoDeveRealizarSaqueParaValorIgualMenorQueZero() {
            ContaBancaria contaBancaria = new ContaBancaria(BigDecimal.TEN);

            Executable executable = () -> contaBancaria.saque(BigDecimal.ZERO);
            IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, executable);

            assertEquals("Valor de saque não pode ser nulo ou menor/igual zero", illegalArgumentException.getMessage());
        }

        @Test
        public void naoDeveRealizarSaqueSeSaldoInsuficiente() {
            ContaBancaria contaBancaria = new ContaBancaria(BigDecimal.ONE);

            Executable executable = () -> contaBancaria.saque(BigDecimal.TEN);
            RuntimeException runtimeException = assertThrows(RuntimeException.class, executable);

            assertEquals("Valor de saldo insuficiente para valor de saque", runtimeException.getMessage());
        }

        @Test
        public void naoDeveRealizarDepositoParaValorNulo() {
            ContaBancaria contaBancaria = new ContaBancaria(BigDecimal.ONE);

            Executable executable = () -> contaBancaria.deposito(null);
            IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, executable);

            assertEquals("Valor de deposito não pode ser nulo ou menor/igual zero", illegalArgumentException.getMessage());
        }

        @Test
        public void naoDeveRealizarDepositoParaValorMenorIgualZero() {
            ContaBancaria contaBancaria = new ContaBancaria(BigDecimal.ONE);

            Executable executable = () -> contaBancaria.deposito(BigDecimal.ZERO);
            IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, executable);

            assertEquals("Valor de deposito não pode ser nulo ou menor/igual zero", illegalArgumentException.getMessage());
        }
    }
}