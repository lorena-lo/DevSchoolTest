Feature: Transaction

  Scenario: User wants to transfer money to another account
    Given an account for name Dorian does not exist
    And an account for name Eduard does not exist
    And an account has been created for Dorian with balance 1000.0
    And an account has been created for Eduard with balance 1500.0
    When Dorian wants to transfer to Eduard the amount of 500.0
    Then status code 200 is received
    And the current balance for Dorian is 500.0
    And the current balance for Eduard is 2000.0
