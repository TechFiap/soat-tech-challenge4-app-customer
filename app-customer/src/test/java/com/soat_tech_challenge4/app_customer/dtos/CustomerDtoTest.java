package com.soat_tech_challenge4.app_customer.dtos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDtoTest {

    @Test
    @DisplayName("Deve criar DTO usando construtor completo")
    void shouldCreateDtoUsingFullConstructor() {
        CustomerDto dto = new CustomerDto(
                "1",
                "Fabio",
                "fabio@email.com",
                "12345678900",
                "senha123"
        );

        assertEquals("1", dto.getId());
        assertEquals("Fabio", dto.getName());
        assertEquals("fabio@email.com", dto.getEmail());
        assertEquals("12345678900", dto.getCpf());
        assertEquals("senha123", dto.getPassword());
    }

    @Test
    @DisplayName("Deve permitir alteração de todos os campos via setters")
    void shouldAllowSettersToModifyFields() {
        CustomerDto dto = new CustomerDto(null, null, null, null, null);

        dto.setId("2");
        dto.setName("João");
        dto.setEmail("joao@email.com");
        dto.setCpf("98765432100");
        dto.setPassword("novaSenha");

        assertEquals("2", dto.getId());
        assertEquals("João", dto.getName());
        assertEquals("joao@email.com", dto.getEmail());
        assertEquals("98765432100", dto.getCpf());
        assertEquals("novaSenha", dto.getPassword());
    }
}