package com.soat_tech_challenge4.app_customer.api.data.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")

public class CustomerEntity {
    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private String cpf;
}

