package com.soat_tech_challenge4.app_customer.core.presenters;

import com.soat_tech_challenge4.app_customer.api.rest.dto.response.CustomerResponseDto;
import com.soat_tech_challenge4.app_customer.core.entities.Customer;

public class CustomerPresenter {
    private CustomerPresenter(){}
    public static CustomerResponseDto toDto(Customer customer) {
        return new CustomerResponseDto(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getCpf());

    }
}