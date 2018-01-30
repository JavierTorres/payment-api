@Feature-1

Feature: As a Client I want to find a payment

Scenario: It is not possible to find a non existing payment
    When a client request for a non existing payment
    Then the response status is 404

Scenario: It is possible to find an existing payment
    Given a client creates a payment passing the required fields
    When a client request the existing payment
    Then the response status is 200
    And the payment is returned in the response
    And the payment includes FX data
