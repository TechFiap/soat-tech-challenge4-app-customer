package com.soat_tech_challenge4.app_customer.api.data;

import com.soat_tech_challenge4.app_customer.api.data.jpa.CustomerEntity;
import com.soat_tech_challenge4.app_customer.api.data.jpa.CustomerJpaRepository;
import com.soat_tech_challenge4.app_customer.core.interfaces.DataSource;
import com.soat_tech_challenge4.app_customer.dtos.CustomerDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class DataRepository implements DataSource {

    private final CustomerJpaRepository customerJpaRepository;
    private final PasswordEncoder passwordEncoder;

    public DataRepository(
            CustomerJpaRepository customerJpaRepository,
            PasswordEncoder passwordEncoder) {
        this.customerJpaRepository = customerJpaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //Recebe um DTO e transforma para Entity do JPA para salvar
    //Devolve um DTO
    @Override
    public void saveCustomer(CustomerDto customerDto) {
        String encryptedPassword = passwordEncoder.encode(customerDto.getPassword());

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(customerDto.getId());
        customerEntity.setName(customerDto.getName());
        customerEntity.setEmail(customerDto.getEmail());
        customerEntity.setPassword(encryptedPassword);
        customerEntity.setCpf(customerDto.getCpf());

        customerJpaRepository.save(customerEntity);

    }

    @Override
    public boolean existsCustomerByEmailOrCpf(String email, String cpf) {

        return customerJpaRepository.existsByEmailOrCpf(email, cpf);
    }

    @Override
    public CustomerDto findCustomerByCpf(String cpf) {
        CustomerEntity entity = customerJpaRepository.findByCpf(cpf);
        if (entity == null) return null;

        return new CustomerDto(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getCpf(),
                entity.getPassword());
    }
}
