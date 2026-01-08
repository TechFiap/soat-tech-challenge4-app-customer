package com.soat_tech_challenge4.app_customer.core.usecases;

import com.soat_tech_challenge4.app_customer.api.rest.dto.request.CustomerRequestDto;
import com.soat_tech_challenge4.app_customer.application.exceptions.ErrorException;
import com.soat_tech_challenge4.app_customer.core.entities.Customer;
import com.soat_tech_challenge4.app_customer.core.gateways.CustomerGateway;

import java.util.UUID;

public record RegisterCustomerUseCase(CustomerGateway customerGateway) {

    public Customer execute(CustomerRequestDto customerRequestDto) {
        boolean customerExists = this.customerGateway.existsByEmailOrCpf(customerRequestDto.getEmail(), customerRequestDto.getCpf());
        if (customerExists) {
            throw new ErrorException("Customer already exists with EMAIL or CPF");
        }
        UUID uuid = UUID.randomUUID();
        Customer customer = new Customer(
                uuid.toString(),
                customerRequestDto.getName(),
                customerRequestDto.getEmail(),
                customerRequestDto.getPassword(),
                customerRequestDto.getCpf());

        this.customerGateway.save(customer);

        return customer;
    }
}
