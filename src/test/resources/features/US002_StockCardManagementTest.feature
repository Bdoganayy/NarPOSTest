@US002
Feature: Stock Card Management

  Scenario: Get to NarPos
    Given I am logged in to NarPos

  Scenario: Delete existing DOMATES stock
    When I delete the DOMATES stock if it exists

  Scenario: Add new DOMATES stock card
    When I add a new DOMATES stock card and veriying

  Scenario: Edit existing DOMATES stock card
    When I edit the DOMATES stock card and verifying that updated

  Scenario: Delete DOMATES-REVIZE stock card
    When I delete the DOMATES-REVIZE stock card and verifying DOMATES-REVIZE stock card is deleted

  Scenario: Add the same DOMATES stock card again
    When I try to add the same DOMATES stock card again
    Then I shouldn't see a name conflict error
