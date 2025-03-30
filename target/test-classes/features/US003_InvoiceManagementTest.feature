@US003
Feature: Invoice Management

  Scenario: User logs in and manages invoices
    Given user is on the NarPos login page
    When user logs in with valid credentials
    Then user navigates to purchase invoice page

  Scenario: User adds a new invoice
    When user adds a new invoice
    Then user verifies the invoice details

  Scenario: User deletes the invoice
    Then user deletes the added invoice

