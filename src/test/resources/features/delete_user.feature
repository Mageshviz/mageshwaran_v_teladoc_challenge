Feature: Delete a user from the web table

  Scenario: Delete the user "novak" from the table and validate the user has been deleted
    Given I am on the webtable pages
    When I delete the user "novak"
    Then I should not see the user "novak" in the webtable
