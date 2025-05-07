package com.algaworks.junit.utilidade;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class SaudacaoUtilTest {
    @Test
    @DisplayName("Deve retornar Bom dia apartir das 5h")
    public void deveSaudarBomDiaApartir5h() {
        String saudacao = SaudacaoUtil.saudar(5);

        assertEquals("Bom dia", saudacao, "Saudação correta");
    }

    @Test
    @DisplayName("Deve saudar Bom dia")
    public void deveSaudarBomDia() {
        // Padrão Tiple A

        // Arrange
        int horaValida = 9;

        // Act
        String saudacao = SaudacaoUtil.saudar(horaValida);

        // Assert
        assertEquals("Bom dia", saudacao, "Saudação correta");
    }

    @Test
    @DisplayName("Deve saudar Boa tarde")
    public void deveSaudarBoaTarde() {
        String saudacao = SaudacaoUtil.saudar(13);

        assertEquals("Boa tarde", saudacao);
    }

    @Test
    @DisplayName("Deve saudar Boa noite")
    public void deveSaudarBoaNoite() {
        String saudacao = SaudacaoUtil.saudar(20);

        assertEquals("Boa noite", saudacao);
    }

    @Test
    @DisplayName("Deve saudar Boa noite até as 4h")
    public void deveSaudarBoaNoiteAte4h() {
        String saudacao = SaudacaoUtil.saudar(4);

        assertEquals("Boa noite", saudacao);
    }

    @Test
    @DisplayName("Não deve lançar exception")
    public void naoDeveLancarException() {
        Executable executable = () -> SaudacaoUtil.saudar(0);
        assertDoesNotThrow(executable);
    }

    @Test
    @DisplayName("Deve lançar exception")
    public void deveLancarException() {
        Executable executable = () -> SaudacaoUtil.saudar(-10);
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, executable);

        assertEquals("Hora inválida", illegalArgumentException.getMessage());
    }
}