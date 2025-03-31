package com.algaworks.junit.utilidade;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SaudacaoUtilTest {
    @Test
    @DisplayName("Testando sucesso da mensagem de bom dia, a partir das 5h")
    public void saudarBomDiaAApartir5h() {
        String saudacao = SaudacaoUtil.saudar(5);
        assertEquals("Bom dia", saudacao, "Saudação de bom dia incorreta");
    }

    @Test
    @DisplayName("Testando sucesso da mensagem de bom dia")
    public void saudarBomDia() {
        String saudacao = SaudacaoUtil.saudar(9);
        assertEquals("Bom dia", saudacao, "Saudação de bom dia incorreta");
    }

    @Test
    @DisplayName("Testando sucesso da mensagem de boa tarde")
    public void saudarBoaTarde() {
        String saudacao = SaudacaoUtil.saudar(15);
        assertEquals("Boa tarde", saudacao, "Saudação de boa tarde incorreta");
    }

    @Test
    @DisplayName("Testando sucesso da mensagem de boa noite")
    public void saudarBoaNoite() {
        String saudacao = SaudacaoUtil.saudar(21);
        assertEquals("Boa noite", saudacao, "Saudação de boa noite incorreta");
    }

    @Test
    @DisplayName("Testando sucesso da mensagem de boa noite, até as 4h")
    public void saudarBoaNoiteAte4h() {
        String saudacao = SaudacaoUtil.saudar(4);
        assertEquals("Boa noite", saudacao, "Saudação de boa noite incorreta");
    }

    @Test
    @DisplayName("Testando sucesso da exception")
    public void deveLancarException() {
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class,
                () -> SaudacaoUtil.saudar(-10));
        assertEquals("Hora inválida", illegalArgumentException.getMessage());
    }

    @Test
    @DisplayName("Testando o que não deve lançar uma exception")
    public void naoDeveLancarException(){
        assertDoesNotThrow(() -> SaudacaoUtil.saudar(0));
    }
}