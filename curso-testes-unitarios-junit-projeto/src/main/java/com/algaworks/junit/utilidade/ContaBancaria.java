package com.algaworks.junit.utilidade;

import java.math.BigDecimal;

public class ContaBancaria {
    private BigDecimal saldo;

    public ContaBancaria(BigDecimal saldo) {
        if (saldo != null) {
            this.saldo = saldo;
        }
        else {
            throw new IllegalArgumentException("Valor de saldo não pode ser nulo");
        }
    }

    private boolean validaValorSaqueEDeposito(BigDecimal valor) {
        if (valor != null && valor.compareTo(BigDecimal.ZERO) > 0) {
            return true;
        }
        else {
            throw new IllegalArgumentException("Valor não pode ser nulo nem menor igual a zero");
        }
    }

    private boolean validaSaldoParaSaque(BigDecimal valor) {
        if (this.saldo.compareTo(valor) > 0) {
            return true;
        }
        else {
            throw new RuntimeException("Valor de saque/deposito maior que valor de saldo");
        }
    }

    public void saque(BigDecimal valor) {
        if (validaValorSaqueEDeposito(valor) && validaSaldoParaSaque(valor)) {
            this.saldo = this.saldo.subtract(valor);
        }
    }

    public void deposito(BigDecimal valor) {
        if (validaValorSaqueEDeposito(valor)) {
            this.saldo = this.saldo.add(valor);
        }
    }

    public BigDecimal saldo() {
        return this.saldo;
    }
}
