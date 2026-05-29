@sprint1
Feature: Login Page

  Background:
    Given the user opens the "Sauce Demo" website
    Then the current page should be the "Sauce Demo" page


  @smoke @regression
  Scenario: Verify that the components are displayed
    Then the current page should be the "Sauce Demo" page
    And the "username" "field" should be displayed
    And the "password" "field" should be displayed
    And the "login" "button" should be displayed

  @smoke @regression
  Scenario Outline: Verify that a valid credentials redirects the user to the Products Page
    When the user logs in as "<username>" from "validUsers"
    Then the current page should be the "Products" page
    Examples:
      | username                |
      | standard_user           |
      | problem_user            |
      | performance_glitch_user |
      | error_user              |
      | visual_user             |

  Scenario: Verify that an invalid credentials displays an error message
    When the user enters "Test" in the "username" field
    And the user enters "Test" in the "password" field
    And the user clicks the "login" button
    Then the error message "Epic sadface: Username and password do not match any user in this service" should be displayed
    And the "error close" "button" should be displayed

  Scenario: Verify that an empty username field displays an error message
    When the user enters "secret_sauce" in the "password" field
    And the user clicks the "login" button
    Then the error message "Epic sadface: Username is required" should be displayed
    And the "error close" "button" should be displayed

    Scenario: Verify that an empty password field displays an error message
      When the user enters "standard_user" in the "username" field
      And the user clicks the "login" button
      Then the error message "Epic sadface: Password is required" should be displayed
      And the "error close" "button" should be displayed

    @smoke @regression
    Scenario: Verify that "locked_out_user" displays and error message
      When the user logs in as "locked_out_user" from "lockedUsers"
      Then the error message "Epic sadface: Sorry, this user has been locked out." should be displayed
      And the "error close" "button" should be displayed
