Feature: Account

  Scenario: An user wants to create an account
    Given an account for name Gigel does not exist
    When an account has been created for Gigel with balance 1000.0
    Then status code 200 is received
    And the account should be created for Gigel
    And the current balance for Gigel is 1000.0
