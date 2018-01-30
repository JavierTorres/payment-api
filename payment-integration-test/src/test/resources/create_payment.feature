@Feature-1

Feature: As a Client I want to create a payment

Scenario: It is not possible to create a payment without required fields
    When a client creates a payment without the required fields
    Then the response status is 400

Scenario: It is possible to create a payment with required fields
    When a client creates a payment passing the required fields
    Then the response status is 201
    And the payment is returned in the response