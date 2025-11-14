Feature: Validating Login Functionality
@Login
  Scenario: Valid Login
    Given User is on Login Page
    When User click on Login button
    Then User will see Login page
    When User give Login Username and Password
    And click on Login button
    Then User will see popup as Login successful message
