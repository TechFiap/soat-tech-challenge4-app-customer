package com.soat_tech_challenge4.app_customer.api.data;

import com.soat_tech_challenge4.app_customer.api.data.jpa.CustomerEntity;
import com.soat_tech_challenge4.app_customer.api.data.jpa.CustomerJpaRepository;
import com.soat_tech_challenge4.app_customer.dtos.CustomerDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DataRepositoryTest {

    private CustomerJpaRepository customerJpaRepository;
    private PasswordEncoder passwordEncoder;
    private DataRepository dataRepository;

    @BeforeEach
    void setup() {
        customerJpaRepository = mock(CustomerJpaRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        dataRepository = new DataRepository(customerJpaRepository, passwordEncoder);
    }

    @Test
    @DisplayName("Deve salvar o cliente com senha criptografada")
    void shouldSaveCustomerWithEncryptedPassword() {
        CustomerDto dto = new CustomerDto(
                "1L",
                "Fabio",
                "fabio@email.com",
                "12345678900",
                "senha123"
        );

        when(passwordEncoder.encode("senha123")).thenReturn("encrypted");

        dataRepository.saveCustomer(dto);

        ArgumentCaptor<CustomerEntity> captor = ArgumentCaptor.forClass(CustomerEntity.class);
        verify(customerJpaRepository).save(captor.capture());

        CustomerEntity saved = captor.getValue();

        assertEquals(dto.getId(), saved.getId());
        assertEquals(dto.getName(), saved.getName());
        assertEquals(dto.getEmail(), saved.getEmail());
        assertEquals(dto.getCpf(), saved.getCpf());
        assertEquals("encrypted", saved.getPassword());
    }

    @Test
    @DisplayName("Deve verificar existência de cliente por email ou CPF")
    void shouldCheckIfCustomerExistsByEmailOrCpf() {
        when(customerJpaRepository.existsByEmailOrCpf("a@a.com", "123")).thenReturn(true);

        boolean result = dataRepository.existsCustomerByEmailOrCpf("a@a.com", "123");

        assertTrue(result);
        verify(customerJpaRepository).existsByEmailOrCpf("a@a.com", "123");
    }

    @Test
    @DisplayName("Deve retornar null quando cliente não for encontrado por CPF")
    void shouldReturnNullWhenCustomerNotFoundByCpf() {
        when(customerJpaRepository.findByCpf("123")).thenReturn(null);

        CustomerDto result = dataRepository.findCustomerByCpf("123");

        assertNull(result);
    }

    @Test
    @DisplayName("Deve retornar DTO quando cliente for encontrado por CPF")
    void shouldReturnCustomerDtoWhenFoundByCpf() {
        CustomerEntity entity = new CustomerEntity();
        entity.setId("1L");
        entity.setName("Fabio");
        entity.setEmail("fabio@email.com");
        entity.setCpf("12345678900");
        entity.setPassword("encrypted");

        when(customerJpaRepository.findByCpf("12345678900")).thenReturn(entity);

        CustomerDto dto = dataRepository.findCustomerByCpf("12345678900");

        assertNotNull(dto);
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getName(), dto.getName());
        assertEquals(entity.getEmail(), dto.getEmail());
        assertEquals(entity.getCpf(), dto.getCpf());
        assertEquals(entity.getPassword(), dto.getPassword());
    }
}