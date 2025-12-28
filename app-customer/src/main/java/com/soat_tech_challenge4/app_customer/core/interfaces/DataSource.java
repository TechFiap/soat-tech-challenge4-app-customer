package com.soat_tech_challenge4.app_customer.core.interfaces;

import com.soat_tech_challenge4.app_customer.dtos.CustomerDto;

public interface DataSource {
    void saveCustomer(CustomerDto customerDto);

    boolean existsCustomerByEmailOrCpf(String email, String cpf);

    CustomerDto findCustomerByCpf(String cpf);
}
