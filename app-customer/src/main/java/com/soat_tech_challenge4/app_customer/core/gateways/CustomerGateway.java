package com.soat_tech_challenge4.app_customer.core.gateways;

import com.soat_tech_challenge4.app_customer.core.entities.Customer;
import com.soat_tech_challenge4.app_customer.core.interfaces.DataSource;
import com.soat_tech_challenge4.app_customer.core.interfaces.ICustomerGateway;
import com.soat_tech_challenge4.app_customer.dtos.CustomerDto;

public record CustomerGateway(DataSource dataSource) implements ICustomerGateway {

    // Recebe uma entidade do Domain transforma em DTO para o dataSource.
    // O dataSource recebe o DTO e transforma em entity do JPA para salvar no banco.
    //Gateway retorna a entidade
    @Override
    public void save(Customer customer) {
        CustomerDto customerDto = new CustomerDto(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getCpf(),
                customer.getPassword());

        dataSource.saveCustomer(customerDto);
    }

    @Override
    public boolean existsByEmailOrCpf(String email, String cpf) {
        return dataSource.existsCustomerByEmailOrCpf(email, cpf);
    }

    @Override
    public Customer findByCpf(String cpf) {

        CustomerDto customerDto = dataSource.findCustomerByCpf(cpf);
        if (customerDto == null) {
            return null;
        }

        return new Customer(
                customerDto.getId(),
                customerDto.getName(),
                customerDto.getEmail(),
                customerDto.getPassword(),
                customerDto.getCpf());
    }
}
