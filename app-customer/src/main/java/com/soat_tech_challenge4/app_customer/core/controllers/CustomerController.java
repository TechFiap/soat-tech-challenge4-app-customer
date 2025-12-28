package com.soat_tech_challenge4.app_customer.core.controllers;

import com.soat_tech_challenge4.app_customer.api.rest.dto.request.CustomerRequestDto;
import com.soat_tech_challenge4.app_customer.api.rest.dto.response.CustomerResponseDto;
import com.soat_tech_challenge4.app_customer.core.entities.Customer;
import com.soat_tech_challenge4.app_customer.core.gateways.CustomerGateway;
import com.soat_tech_challenge4.app_customer.core.interfaces.DataSource;
import com.soat_tech_challenge4.app_customer.core.presenters.CustomerPresenter;
import com.soat_tech_challenge4.app_customer.core.usecases.FindCustomerByCpfUseCase;
import com.soat_tech_challenge4.app_customer.core.usecases.RegisterCustomerUseCase;

public class CustomerController {

    private final DataSource dataSource;

    public CustomerController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto) {
        //Instancia um CustomerGateway que sera injetado para useCase utilizar
        //Instancia um RegisterCustomerUseCase e executa o useCase passando o DTO com os dados
        CustomerGateway customerGateway = new CustomerGateway(dataSource);
        RegisterCustomerUseCase registerCustomerUseCase = new RegisterCustomerUseCase(customerGateway);

        Customer customer = registerCustomerUseCase.execute(customerRequestDto);

        //Presenter que sera utilizado para formatar os dados (mapper)
        return CustomerPresenter.toDto(customer);
    }

    public CustomerResponseDto findByCpf(String cpf) {
        CustomerGateway customerGateway = new CustomerGateway(dataSource);
        FindCustomerByCpfUseCase findCustomerByCpfUseCase = new FindCustomerByCpfUseCase(customerGateway);

        Customer customer = findCustomerByCpfUseCase.execute(cpf);

        return CustomerPresenter.toDto(customer);

    }
}
