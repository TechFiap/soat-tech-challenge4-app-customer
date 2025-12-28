package com.soat_tech_challenge4.app_customer.api.data.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, Long> {

    boolean existsByEmailOrCpf(String email, String cpf);

    CustomerEntity findByCpf(String cpf);
}