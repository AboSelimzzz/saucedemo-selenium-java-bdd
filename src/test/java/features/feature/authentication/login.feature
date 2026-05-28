Feature: Login Page

  Background:
      Given the user opens the "login" page


  Scenario: Verify the visibility of the components
    Then the "login" page is displayed
    And the "username" field is displayed
    And the "password" field is displayed
    And the "login" button is displayed
