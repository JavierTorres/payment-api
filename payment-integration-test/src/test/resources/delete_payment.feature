@Feature-1

Feature: As a Client I want to delete a payment

Scenario: It is not possible to delete a non existing payment
    When a client request to delete a non existing payment
    Then the response status is 404

Scenario: It is possible to delete an existing payment
    Given a client creates a payment passing the required fields
    When a client request to delete a existing payment
    Then the response status is 202