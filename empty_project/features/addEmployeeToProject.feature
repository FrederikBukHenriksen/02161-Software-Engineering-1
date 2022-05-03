Feature: Add an employee to project
  Description: Allow a project leader to Add employees to project

Background: Create project
Given that there exists a project titled "Software Development" with id "2022-1"
And JUNIT create an employee "fred"
And JUNIT create an employee "andr"
And set user "fred" as project leader for project "2022-1"

Scenario: Add an employee to project
Given that the project leader of the project "2022-1" is logged in
And the user "andr" is not in the project "2022-1"
When the user "andr" is added to the project "2022-1"
Then the user "andr" is in the project "2022-1"

Scenario: Add an employee to project which already is assigned
Given that the project leader of the project "2022-1" is logged in
And the user "andr" is added to the project "2022-1"
When the user "andr" is added to the project "2022-1"
Then the error message "User is already on the project" is given
And the user "andr" is only once in the project "2022-1"

Scenario: Add an employee to project when the project leader is not logged in
Given that the project leader of the project "2022-1" is not logged in
And the user "andr" is not in the project "2022-1"
When the user "andr" is added to the project "2022-1"
Then the user "andr" is not in the project "2022-1"
And the error message "Only a project leader can add an employee to the project" is given