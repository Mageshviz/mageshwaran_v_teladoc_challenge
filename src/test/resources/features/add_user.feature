Feature: Add a user to the web table

  Scenario: Add a user and validate the user has been added to the table
    Given I am on the webtable page
    When I add a user with the following details:
      | First Name | Last Name | User Name | Email        | Role     | Cell Phone |
      | novak       | Doe       | novak.doe  | novak@doe.com | Customer | 1234567890 |
    Then I should see the user "novak.doe" in the webtable
