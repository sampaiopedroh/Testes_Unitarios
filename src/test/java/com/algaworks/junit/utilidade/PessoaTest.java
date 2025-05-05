package com.algaworks.junit.utilidade;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PessoaTest {
    @Test
    public void assercaoAgrupada() {
        Pessoa pessoa = new Pessoa("Pedro", "Sampaio");

        // Bom para validar vários parâmetros
        assertAll("Asserções de pessoa",
                () -> assertEquals("Pedro", pessoa.getNome()),
                () -> assertEquals("Sampaio", pessoa.getSobrenome())
        );
    }
}