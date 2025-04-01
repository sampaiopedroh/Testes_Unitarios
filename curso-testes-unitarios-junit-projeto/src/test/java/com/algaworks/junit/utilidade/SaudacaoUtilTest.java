package com.algaworks.junit.utilidade;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.function.Executable;

import static com.algaworks.junit.utilidade.SaudacaoUtil.saudar;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes de saudações")
class SaudacaoUtilTest {
    @Test
    @DisplayName("Testando sucesso da mensagem de bom dia, a partir das 5h")
    public void saudarBomDiaAApartir5h() {
        // Padrão Tiple A

        // Arrange
        int horaValida = 5;

        // Act
        String saudacao = saudar(horaValida);

        // Assert
        assertEquals("Bom dia", saudacao, "Saudação de bom dia incorreta");
    }

    @Test
    @DisplayName("Testando sucesso da mensagem de bom dia")
    public void saudarBomDia() {
        String saudacao = saudar(9);
        assertEquals("Bom dia", saudacao, "Saudação de bom dia incorreta");
    }

    @Test
    @DisplayName("Testando sucesso da mensagem de boa tarde")
    public void saudarBoaTarde() {
        String saudacao = saudar(15);
        assertEquals("Boa tarde", saudacao, "Saudação de boa tarde incorreta");
    }

    @Test
    @DisplayName("Testando sucesso da mensagem de boa noite")
    public void saudarBoaNoite() {
        String saudacao = saudar(21);
        assertEquals("Boa noite", saudacao, "Saudação de boa noite incorreta");
    }

    @Test
    @DisplayName("Testando sucesso da mensagem de boa noite, até as 4h")
    public void saudarBoaNoiteAte4h() {
        String saudacao = saudar(4);
        assertEquals("Boa noite", saudacao, "Saudação de boa noite incorreta");
    }

    @Test
    @DisplayName("Testando sucesso da exception")
    public void deveLancarException() {
        int horaInvalida = -10;

        // Jeito I
        // IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
        //         () -> saudar(horaInvalida));

        // Jeito II
        Executable executable = () -> saudar(horaInvalida);
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, executable);


        assertEquals("Hora inválida", illegalArgumentException.getMessage());
    }

    @Test
    @DisplayName("Testando o que não deve lançar uma exception")
    public void naoDeveLancarException(){
        int horaValida = 0;

        Executable executable = () -> saudar(horaValida);

        assertDoesNotThrow(executable);
    }
}