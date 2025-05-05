package com.algaworks.junit.utilidade;

import java.math.BigDecimal;

public class ContaBancaria {
    private BigDecimal saldo;

    public ContaBancaria(BigDecimal saldo) {
        //TODO 1 - validar saldo: não pode ser nulo, caso seja, deve lançar uma IllegalArgumentException
        //TODO 2 - pode ser zero ou negativo

        if(!(saldo == null)) {
            this.saldo = saldo;
        }
        else {
            throw new IllegalArgumentException("Saldo não pode ser nulo");
        }
    }

    public void saque(BigDecimal valor) {
        //TODO 1 - validar valor: não pode ser nulo, zero ou menor que zero, caso seja, deve lançar uma IllegalArgumentException
        //TODO 2 - Deve subtrair o valor do saldo
        //TODO 3 - Se o saldo for insuficiente deve lançar uma RuntimeException

        /*
        O método compareTo retorna um valor inteiro que indica a relação entre as duas instâncias:

        -1: se a instância de BigDecimal na qual o método é chamado for numericamente menor que a instância passada como argumento.
        0: se as duas instâncias de BigDecimal forem numericamente iguais.
        1: se a instância de BigDecimal na qual o método é chamado for numericamente maior que a instância passada como argumento.
        */

        /*
        A forma idiomática de usar compareTo para comparações booleanas é:

        Menor que: x.compareTo(y) < 0
        Menor ou igual a: x.compareTo(y) <= 0
        Igual a: x.compareTo(y) == 0
        Diferente de: x.compareTo(y) != 0
        Maior ou igual a: x.compareTo(y) >= 0
        Maior que: x.compareTo(y) > 0
         */

        if (!(valor == null || valor.compareTo(BigDecimal.ZERO) <= 0)) {
            if (!(this.saldo.compareTo(valor) < 0)) {
                this.saldo = saldo.subtract(valor);
            } else {
                throw new RuntimeException("Valor de saldo insuficiente para valor de saque");
            }
        } else {
            throw new IllegalArgumentException("Valor de saque não pode ser nulo ou menor/igual zero");
        }
    }

    public void deposito(BigDecimal valor) {
        //TODO 1 - validar valor: não pode ser nulo, zero ou menor que zero, caso seja, deve lançar uma IllegalArgumentException
        //TODO 2 - Deve adicionar o valor ao saldo

        if (!(valor == null || valor.compareTo(BigDecimal.ZERO) <= 0)) {
            this.saldo = saldo.add(valor);
        } else {
            throw new IllegalArgumentException("Valor de deposito não pode ser nulo ou menor/igual zero");
        }
    }

    public BigDecimal saldo() {
        //TODO 1 - retornar saldo
        return this.saldo;
    }
}
