package com.soat_tech_challenge4.app_customer.bdd.steps;

import com.soat_tech_challenge4.app_customer.core.entities.Customer;
import com.soat_tech_challenge4.app_customer.application.exceptions.InvalidCpfException;
import io.cucumber.java.en.*;

import static org.junit.Assert.*;

public class CustomerSteps {

    private String id;
    private String name;
    private String email;
    private String password;
    private String cpf;

    private Customer customer;
    private Exception exception;

    @Given("I have customer data with a valid CPF")
    public void i_have_customer_data_with_a_valid_cpf() {
        id = "1";
        name = "Fabio";
        email = "fabio@email.com";
        password = "123456";
        cpf = "52998224725"; // valid CPF
    }

    @Given("I have customer data with an invalid CPF")
    public void i_have_customer_data_with_an_invalid_cpf() {
        id = "1";
        name = "Fabio";
        email = "fabio@email.com";
        password = "123456";
        cpf = "11111111111"; // invalid CPF
    }

    @When("I create the customer")
    public void i_create_the_customer() {
        try {
            customer = new Customer(id, name, email, password, cpf);
        } catch (Exception e) {
            exception = e;
        }
    }

    @When("I attempt to create the customer")
    public void i_attempt_to_create_the_customer() {
        try {
            customer = new Customer(id, name, email, password, cpf);
        } catch (Exception e) {
            exception = e;
        }
    }

    @Then("the customer should be created successfully")
    public void the_customer_should_be_created_successfully() {
        assertNotNull(customer);
        assertEquals(cpf, customer.getCpf());
    }

    @Then("an invalid CPF exception should be thrown")
    public void an_invalid_cpf_exception_should_be_thrown() {
        assertNotNull(exception);
        assertTrue(exception instanceof InvalidCpfException);
    }
}
