@Feature-1

Feature: As a Client I want to update a payment

Scenario: It is not possible to update a non existing payment
    When a client request to update a non existing payment
    Then the response status is 404

Scenario: It is possible to update an existing payment
    Given a client creates a payment passing the required fields
    When a client request to update an existing payment
    Then the response status is 200