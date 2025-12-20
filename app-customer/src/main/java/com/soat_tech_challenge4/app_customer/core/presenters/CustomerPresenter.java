package com.soat_tech_challenge4.app_customer.core.presenters;

import com.soat_tech_challenge4.app_customer.api.rest.dto.response.CustomerResponseDto;
import com.soat_tech_challenge4.app_customer.core.entities.Customer;

public class CustomerPresenter {

    public static CustomerResponseDto toDto(Customer customer) {
        return new CustomerResponseDto().builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .cpf(customer.getCpf())
                .build();
    }
}