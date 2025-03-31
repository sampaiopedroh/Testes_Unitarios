package com.algaworks.junit.utilidade;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PessoaTest {
    @Test
    void assercaoAgrupada() {
        Pessoa pessoa = new Pessoa("Pedro", "Sampaio");

        assertAll("Asserções de pessoas",
                () -> assertEquals("Pedro", pessoa.getNome()),
                () -> assertEquals("Sampaio", pessoa.getSobrenome())
                );
    }

}