@Customer
Feature: Customer Login Feature

  Scenario Outline: Validate customer account
    Given use a valid account <ID>
    When get one customer with REST endpoint "<RestEndpoints>"
    And HTTP Method "<HttpMethod>"
    When executed
    Then HTTP status code should be <ResponseCode>

  Examples:
    |ID|RestEndpoints|HttpMethod|ResponseCode|
    |1 |/customers    |GET      |302         |
    |2 |/customers    |GET      |302         |
    |3 |/customers    |GET      |302         |