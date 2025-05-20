package com.algaworks.junit.utilidade;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class ContaBancariaBDDTest {
    private ContaBancaria conta;

    @Nested
    @DisplayName("Dado uma conta com saldo de R$10.00")
    class ContaBancariaComSaldo {
        @BeforeEach
        void beforeEach() {
            conta = new ContaBancaria(BigDecimal.TEN);
        }

        @Nested
        @DisplayName("Quando efetuar saque com valor menor")
        class SaqueValorMenor {
            private BigDecimal saque = new BigDecimal("9.0");

            @Test
            @DisplayName("Então não deve lançar exception")
            void naoDeveLancarException() {
                assertDoesNotThrow(() -> conta.saque(saque));
            }

            @Test
            @DisplayName("Deve subtrair do saldo")
            void deveSubtrairDoSaldo() {
                conta.saque(saque);

                assertEquals(new BigDecimal("1.0"), conta.saldo());
            }
        }

        @Nested
        @DisplayName("Quando efetuar saque com valor maior")
        class SaqueValorMaior {
            private BigDecimal saque = new BigDecimal("11.00");

            @Test
            @DisplayName("Então deve lançar exception")
            void deveLancarException() {
                Executable executable = () -> conta.saque(saque);
                RuntimeException runtimeException = assertThrows(RuntimeException.class, executable);

                assertEquals("Valor de saldo insuficiente para valor de saque", runtimeException.getMessage());
            }

            @Test
            @DisplayName("Não deve alterar o saldo")
            void naoDeveAlterarSaldo() {
                // Assim
//                Executable executable = () -> conta.saque(saque);
//                RuntimeException runtimeException = assertThrows(RuntimeException.class, executable);
//                assertEquals(BigDecimal.TEN, conta.saldo());

                // Ou assim
                try {
                    conta.saque(saque);
                } catch (Exception e) {
                    assertEquals(BigDecimal.TEN, conta.saldo());
                }
            }
        }
    }

    @Nested
    @DisplayName("Dado uma conta com saldo R$0.0")
    class ContaBancariaSemSaldo {
        @BeforeEach
        void beforeEach() {
            conta = new ContaBancaria(BigDecimal.ZERO);
        }

        @Nested
        @DisplayName("Quando efetuar saque")
        class SaqueValorMaior {
            private BigDecimal saque = new BigDecimal("11.00");

            @Test
            @DisplayName("Então deve lançar exception")
            void deveLancarException() {
                Executable executable = () -> conta.saque(saque);
                RuntimeException runtimeException = assertThrows(RuntimeException.class, executable);

                assertEquals("Valor de saldo insuficiente para valor de saque", runtimeException.getMessage());
            }
        }

        @Nested
        @DisplayName("Quando efetuar deposito de R$10.00")
        class DepositoDezReais {
            private BigDecimal deposito = BigDecimal.TEN;

            @Test
            @DisplayName("Então deve adicionar ao saldo")
            void deveAdicionarAoSaldo() {
                conta.deposito(deposito);
                assertEquals(BigDecimal.TEN, conta.saldo());
            }
        }
    }
}
