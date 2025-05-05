package com.algaworks.junit.utilidade;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class SaudacaoUtilTest {
    @Test
    public void deveSaudarBomDiaApartir5h() {
        String saudacao = SaudacaoUtil.saudar(5);

        assertEquals("Bom dia", saudacao, "Saudação correta");
    }

    @Test
    public void deveSaudarBomDia() {
        String saudacao = SaudacaoUtil.saudar(9);

        assertEquals("Bom dia", saudacao, "Saudação correta");
    }

    @Test
    public void deveSaudarBoaTarde() {
        String saudacao = SaudacaoUtil.saudar(13);

        assertEquals("Boa tarde", saudacao);
    }

    @Test
    public void deveSaudarBoaNoite() {
        String saudacao = SaudacaoUtil.saudar(20);

        assertEquals("Boa noite", saudacao);
    }

    @Test
    public void deveSaudarBoaNoiteAte4h() {
        String saudacao = SaudacaoUtil.saudar(4);

        assertEquals("Boa noite", saudacao);
    }

    @Test
    public void naoDeveLancarException() {
        Executable executable = () -> SaudacaoUtil.saudar(0);
        assertDoesNotThrow(executable);
    }

    @Test
    public void deveLancarException() {
        Executable executable = () -> SaudacaoUtil.saudar(-10);
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, executable);

        assertEquals("Hora inválida", illegalArgumentException.getMessage());
    }
}