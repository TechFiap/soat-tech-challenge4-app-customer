package com.soat_tech_challenge4.app_customer.dtos;

public record CustomerDto(
        String id,
        String name,
        String email,
        String cpf,
        String password) {
}
