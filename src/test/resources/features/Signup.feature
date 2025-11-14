Feature: Validating Signup Functionality

  Scenario: Valid Signup
    Given User is on Sign Page
    When User click on Signup button
    Then User will see Signup page
    When User give Signup Username and Password
    And click on Signup button
    Then User will see popup as Signup successful message
