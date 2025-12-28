package com.soat_tech_challenge4.app_customer.core.interfaces;

import com.soat_tech_challenge4.app_customer.core.entities.Customer;

public interface ICustomerGateway {
    void save(Customer customer);

    boolean existsByEmailOrCpf(String email, String cpf);

    Customer findByCpf(String cpf);
}
