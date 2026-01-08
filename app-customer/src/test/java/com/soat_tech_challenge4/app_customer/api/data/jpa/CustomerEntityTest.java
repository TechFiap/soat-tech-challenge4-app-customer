package com.soat_tech_challenge4.app_customer.api.data.jpa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerEntityTest {

    @Test
    @DisplayName("Deve criar entidade usando construtor vazio e setters")
    void shouldCreateEntityUsingEmptyConstructor() {
        CustomerEntity entity = new CustomerEntity();

        entity.setId("1");
        entity.setName("Fabio");
        entity.setEmail("fabio@email.com");
        entity.setPassword("123");
        entity.setCpf("11122233344");

        assertEquals("1", entity.getId());
        assertEquals("Fabio", entity.getName());
        assertEquals("fabio@email.com", entity.getEmail());
        assertEquals("123", entity.getPassword());
        assertEquals("11122233344", entity.getCpf());
    }

    @Test
    @DisplayName("Deve criar entidade usando construtor completo")
    void shouldCreateEntityUsingFullConstructor() {
        CustomerEntity entity = new CustomerEntity(
                "1",
                "Fabio",
                "fabio@email.com",
                "123",
                "11122233344"
        );

        assertEquals("1", entity.getId());
        assertEquals("Fabio", entity.getName());
        assertEquals("fabio@email.com", entity.getEmail());
        assertEquals("123", entity.getPassword());
        assertEquals("11122233344", entity.getCpf());
    }
}