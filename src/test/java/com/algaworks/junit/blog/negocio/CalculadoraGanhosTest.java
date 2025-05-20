package com.algaworks.junit.blog.negocio;

import com.algaworks.junit.blog.modelo.Editor;
import com.algaworks.junit.blog.modelo.Ganhos;
import com.algaworks.junit.blog.modelo.Post;
import com.algaworks.junit.blog.utilidade.ProcessadorTextoSimples;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculadoraGanhosTest {
    static CalculadoraGanhos calculadoraGanhos;
    Post post;
    Editor editor;

    @BeforeAll
    static void beforeAll() {
        calculadoraGanhos = new CalculadoraGanhos(new ProcessadorTextoSimples(), BigDecimal.TEN);
    }

    @BeforeEach
    void beforeEach() {
        Editor editor = new Editor(1L, "Pedro", "pedro@sla.com", new BigDecimal(5), true);
        Post post = new Post(1L, "Curso de Testes", "Um curso sobre testes unitários", editor, "testes", null, false, false);
    }

//    @AfterAll
//    static void afterAll() {
//        System.out.println("Depois de todos");
//    }
//
//    @AfterEach
//    void afterEach() {
//        System.out.println("Depois de cada");
//    }

    @Test
    public void deveCalcularGanhos() {
        Ganhos ganhos = calculadoraGanhos.calcular(post);

        assertEquals(new BigDecimal(35), ganhos.getTotalGanho());
        assertEquals(5, ganhos.getQuantidadePalavras());
    }

    @Test
    public void deveCalcularGanhosSemPremium() {
        editor.setPremium(false);

        Ganhos ganhos = calculadoraGanhos.calcular(post);

        assertEquals(new BigDecimal(25), ganhos.getTotalGanho());
        assertEquals(5, ganhos.getQuantidadePalavras());
    }
}