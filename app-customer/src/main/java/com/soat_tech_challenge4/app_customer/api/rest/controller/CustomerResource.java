package com.soat_tech_challenge4.app_customer.api.rest.controller;

import com.soat_tech_challenge4.app_customer.api.data.DataRepository;
import com.soat_tech_challenge4.app_customer.api.rest.dto.request.CustomerRequestDto;
import com.soat_tech_challenge4.app_customer.api.rest.dto.response.CustomerResponseDto;
import com.soat_tech_challenge4.app_customer.application.exceptions.InvalidCpfException;
import com.soat_tech_challenge4.app_customer.application.validation.CpfValidator;
import com.soat_tech_challenge4.app_customer.core.controllers.CustomerController;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerResource {

    private final DataRepository dataRepository;

    public CustomerResource(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<CustomerResponseDto> create(@RequestBody @Valid CustomerRequestDto customerRequestDto) {
        CustomerController customerController = new CustomerController(dataRepository);
        CustomerResponseDto result = customerController.createCustomer(customerRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }


    @GetMapping("/{cpf}")
    public ResponseEntity<CustomerResponseDto> findByCpf(@PathVariable String cpf) {
        if (!CpfValidator.isValid(cpf)) {
            throw new InvalidCpfException("Invalid CPF: " + cpf);
        }

        CustomerController customerController = new CustomerController(dataRepository);
        CustomerResponseDto response = customerController.findByCpf(cpf);

        return response != null ? ResponseEntity.ok(response) : ResponseEntity.notFound().build();
    }

}
