@Feature-1

Feature: As a Client I want to list payment

Scenario: It is empty list of payments when non existing payments
    Given a empty list of payments
    When list of payments
    Then the response status is 200
    And the number of payments is 0

Scenario: It is possible to find an existing payment
    Given a empty list of payments
    And a client creates a payment passing the required fields
    And a client creates a payment passing the required fields
    When list of payments
    Then the response status is 200
    And the number of payments is 2
