Feature: User Login
  As a registered user
  I want to log in to SauceDemo
  So that I can access the shopping inventory

  Scenario: Login with valid standard user credentials
    Given I am on the SauceDemo login page
    When I login with user
    Then I should be redirected to the inventory page

  Scenario: Login with a locked out user
    Given I am on the SauceDemo login page
    When I login with "locked_out_user" And "secret_sauce"
    Then I should see an error message contain "locked out"
