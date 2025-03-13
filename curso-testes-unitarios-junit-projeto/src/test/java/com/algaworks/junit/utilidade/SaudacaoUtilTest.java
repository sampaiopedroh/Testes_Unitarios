package com.algaworks.junit.utilidade;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SaudacaoUtilTest {
    @Test
    public void saudar() {
        String saudar = SaudacaoUtil.saudar(9);
        Assertions.assertTrue(saudar.equals("Bom dia"));
    }
}