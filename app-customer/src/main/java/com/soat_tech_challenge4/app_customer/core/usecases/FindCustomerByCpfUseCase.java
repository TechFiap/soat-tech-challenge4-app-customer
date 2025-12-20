package com.soat_tech_challenge4.app_customer.core.usecases;

import com.soat_tech_challenge4.app_customer.application.exceptions.ErrorException;
import com.soat_tech_challenge4.app_customer.core.entities.Customer;
import com.soat_tech_challenge4.app_customer.core.gateways.CustomerGateway;

public class FindCustomerByCpfUseCase {

    private final CustomerGateway customerGateway;

    public FindCustomerByCpfUseCase(CustomerGateway customerGateway) {
        this.customerGateway = customerGateway;
    }

    public Customer execute(String cpf) {
        Customer customer = customerGateway.findByCpf(cpf);
        if (customer == null) {
            throw new ErrorException("Customer not found with this CPF");
        }
        return customer;
    }
}