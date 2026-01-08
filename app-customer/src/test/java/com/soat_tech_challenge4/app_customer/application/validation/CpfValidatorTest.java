package com.soat_tech_challenge4.app_customer.application.validation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CpfValidatorTest {

    @Test
    @DisplayName("Deve retornar false quando CPF for null")
    void shouldReturnFalseWhenCpfIsNull() {
        assertFalse(CpfValidator.isValid(null));
    }

    @Test
    @DisplayName("Deve retornar false quando CPF for vazio")
    void shouldReturnFalseWhenCpfIsEmpty() {
        assertFalse(CpfValidator.isValid(""));
    }

    @Test
    @DisplayName("Deve retornar false quando CPF tiver tamanho inválido")
    void shouldReturnFalseWhenCpfHasInvalidLength() {
        assertFalse(CpfValidator.isValid("123"));
        assertFalse(CpfValidator.isValid("1234567890123"));
    }

    @Test
    @DisplayName("Deve retornar false quando CPF tiver todos os dígitos iguais")
    void shouldReturnFalseWhenCpfHasAllDigitsEqual() {
        assertFalse(CpfValidator.isValid("11111111111"));
        assertFalse(CpfValidator.isValid("00000000000"));
    }

    @Test
    @DisplayName("Deve retornar false quando primeiro dígito verificador for inválido")
    void shouldReturnFalseWhenFirstDigitIsInvalid() {
        // CPF válido: 52998224725
        // Alterando o primeiro DV (posição 9)
        assertFalse(CpfValidator.isValid("52998224735"));
    }

    @Test
    @DisplayName("Deve retornar false quando segundo dígito verificador for inválido")
    void shouldReturnFalseWhenSecondDigitIsInvalid() {
        // CPF válido: 52998224725
        // Alterando o segundo DV (posição 10)
        assertFalse(CpfValidator.isValid("52998224724"));
    }

    @Test
    @DisplayName("Deve retornar true para CPF válido sem máscara")
    void shouldReturnTrueForValidCpf() {
        assertTrue(CpfValidator.isValid("52998224725"));
    }

    @Test
    @DisplayName("Deve retornar true para CPF válido com máscara")
    void shouldReturnTrueForValidCpfWithMask() {
        assertTrue(CpfValidator.isValid("529.982.247-25"));
    }
}