package com.soat_tech_challenge4.app_customer.api.rest.dto.response;

public class CustomerResponseDto {
    private final String id;
    private final String name;
    private final String email;
    private final String cpf;

    public CustomerResponseDto(String id, String name, String email, String cpf) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }
}