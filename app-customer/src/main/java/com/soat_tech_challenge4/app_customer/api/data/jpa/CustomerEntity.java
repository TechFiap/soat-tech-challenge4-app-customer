package com.soat_tech_challenge4.app_customer.api.data.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "customers")
@Getter
@Setter
public class CustomerEntity {

    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private String cpf;

}

