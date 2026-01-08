package com.soat_tech_challenge4.app_customer.core.entities;

import com.soat_tech_challenge4.app_customer.application.exceptions.InvalidCpfException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    private static final String VALID_CPF = "52998224725"; // CPF válido real

    @Test
    @DisplayName("Deve criar Customer com CPF válido")
    void shouldCreateCustomerWithValidCpf() {
        Customer customer = new Customer("1", "Fabio", "fabio@email.com", "123", VALID_CPF);

        assertEquals("1", customer.getId());
        assertEquals("Fabio", customer.getName());
        assertEquals("fabio@email.com", customer.getEmail());
        assertEquals("123", customer.getPassword());
        assertEquals(VALID_CPF, customer.getCpf());
    }

    @Test
    @DisplayName("Deve lançar exceção ao criar Customer com CPF inválido")
    void shouldThrowExceptionWhenCpfIsInvalid() {
        assertThrows(
                InvalidCpfException.class,
                () -> new Customer("1", "Fabio", "email", "123", "11111111111")
        );
    }

    @Test
    @DisplayName("validateCpf deve retornar false para CPF null")
    void shouldReturnFalseWhenCpfIsNull() {
        Customer customer = new Customer("1", "Fabio", "email", "123", VALID_CPF);
        assertFalse(customer.validateCpf(null));
    }

    @Test
    @DisplayName("validateCpf deve retornar false para CPF vazio")
    void shouldReturnFalseWhenCpfIsEmpty() {
        Customer customer = new Customer("1", "Fabio", "email", "123", VALID_CPF);
        assertFalse(customer.validateCpf(""));
    }

    @Test
    @DisplayName("validateCpf deve retornar false para CPF com tamanho inválido")
    void shouldReturnFalseWhenCpfHasInvalidLength() {
        Customer customer = new Customer("1", "Fabio", "email", "123", VALID_CPF);
        assertFalse(customer.validateCpf("123"));
        assertFalse(customer.validateCpf("1234567890123"));
    }

    @Test
    @DisplayName("validateCpf deve retornar false para CPF com todos dígitos iguais")
    void shouldReturnFalseWhenCpfHasAllDigitsEqual() {
        Customer customer = new Customer("1", "Fabio", "email", "123", VALID_CPF);
        assertFalse(customer.validateCpf("00000000000"));
        assertFalse(customer.validateCpf("99999999999"));
    }

    @Test
    @DisplayName("validateCpf deve retornar false quando primeiro dígito verificador é inválido")
    void shouldReturnFalseWhenFirstDigitIsInvalid() {
        Customer customer = new Customer("1", "Fabio", "email", "123", VALID_CPF);
        assertFalse(customer.validateCpf("52998224735")); // altera o primeiro DV
    }

    @Test
    @DisplayName("validateCpf deve retornar false quando segundo dígito verificador é inválido")
    void shouldReturnFalseWhenSecondDigitIsInvalid() {
        Customer customer = new Customer("1", "Fabio", "email", "123", VALID_CPF);
        assertFalse(customer.validateCpf("52998224724")); // altera o segundo DV
    }

    @Test
    @DisplayName("validateCpf deve retornar true para CPF válido com máscara")
    void shouldReturnTrueForValidCpfWithMask() {
        Customer customer = new Customer("1", "Fabio", "email", "123", VALID_CPF);
        assertTrue(customer.validateCpf("529.982.247-25"));
    }

    @Test
    @DisplayName("validateCpf deve retornar true para CPF válido sem máscara")
    void shouldReturnTrueForValidCpf() {
        Customer customer = new Customer("1", "Fabio", "email", "123", VALID_CPF);
        assertTrue(customer.validateCpf(VALID_CPF));
    }
}