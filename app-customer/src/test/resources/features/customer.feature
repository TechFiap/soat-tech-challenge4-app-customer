Feature: Customer registration
  In order to ensure that only valid customers are created
  As a registration system
  I want to validate the CPF when creating a Customer

  Scenario: Create a customer with a valid CPF
    Given I have customer data with a valid CPF
    When I create the customer
    Then the customer should be created successfully

  Scenario: Try to create a customer with an invalid CPF
    Given I have customer data with an invalid CPF
    When I attempt to create the customer
    Then an invalid CPF exception should be thrown