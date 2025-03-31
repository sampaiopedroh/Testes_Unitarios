package com.algaworks.junit.utilidade;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ContaBancariaTest {
    @Test
    void deveInformarValorValidoSaldo() {
        BigDecimal valorSaldo = new BigDecimal(300);
        ContaBancaria contaBancaria = new ContaBancaria(valorSaldo);
        assertEquals(valorSaldo, contaBancaria.saldo());
    }

    @Test
    void deveLancarExeptionValorInvalidoSaldo() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> new ContaBancaria(null));

        assertEquals("Valor de saldo não pode ser nulo", illegalArgumentException.getMessage());
    }

    @Test
    void deveRealizarSaque() {
        BigDecimal valorSaldo = new BigDecimal(300);
        ContaBancaria contaBancaria = new ContaBancaria(valorSaldo);
        BigDecimal valorSaque = new BigDecimal(200);
        contaBancaria.saque(valorSaque);
        assertEquals(valorSaldo.subtract(valorSaque), contaBancaria.saldo());
    }

    @Test
    void naoDeveRealizarSaqueComValorNull() {
        BigDecimal valorSaldo = new BigDecimal(300);
        ContaBancaria contaBancaria = new ContaBancaria(valorSaldo);
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> contaBancaria.saque(null));

        assertEquals("Valor não pode ser nulo nem menor igual a zero", illegalArgumentException.getMessage());
    }

    @Test
    void naoDeveRealizarSaqueComValorMenorIgualZero() {
        BigDecimal valorSaldo = new BigDecimal(300);
        ContaBancaria contaBancaria = new ContaBancaria(valorSaldo);
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> contaBancaria.saque(new BigDecimal(0)));

        assertEquals("Valor não pode ser nulo nem menor igual a zero", illegalArgumentException.getMessage());
    }

    @Test
    void naoDeveRealizarSaqueComValorMaiorSaldo() {
        BigDecimal valorSaldo = new BigDecimal(300);
        ContaBancaria contaBancaria = new ContaBancaria(valorSaldo);
        RuntimeException runtimeException = assertThrows(RuntimeException.class,
                () -> contaBancaria.saque(new BigDecimal(301)));

        assertEquals("Valor de saque/deposito maior que valor de saldo", runtimeException.getMessage());
    }

    @Test
    void deveRealizarDeposito() {
        BigDecimal valorSaldo = new BigDecimal(300);
        ContaBancaria contaBancaria = new ContaBancaria(valorSaldo);
        BigDecimal valorDeposito = new BigDecimal(200);
        contaBancaria.deposito(valorDeposito);
        assertEquals(valorSaldo.add(valorDeposito), contaBancaria.saldo());
    }

    @Test
    void naoDeveRealizarDepositoComValorNull() {
        BigDecimal valorSaldo = new BigDecimal(300);
        ContaBancaria contaBancaria = new ContaBancaria(valorSaldo);
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> contaBancaria.deposito(null));

        assertEquals("Valor não pode ser nulo nem menor igual a zero", illegalArgumentException.getMessage());
    }

    @Test
    void naoDeveRealizarDepositoComValorMenorIgualZero() {
        BigDecimal valorSaldo = new BigDecimal(300);
        ContaBancaria contaBancaria = new ContaBancaria(valorSaldo);
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> contaBancaria.saque(new BigDecimal(0)));

        assertEquals("Valor não pode ser nulo nem menor igual a zero", illegalArgumentException.getMessage());
    }
}