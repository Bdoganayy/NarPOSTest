@US001
Feature: User login functionality

  Scenario: Incorrect login entry
    Given User navigates to the login page
    When User enters incorrect email and password
    Then User should see an incorrect login message

  Scenario: Successful login
    Given User navigates to the login page
    When User enters correct email and password
    Then User should be redirected to the homepage
