Feature: Add an employee to project
  Description: Allow a project leader to Add employees to project


Scenario: Add an employee to project
Given that there exists a project titled "Software Development" with id "2022-1"
And that the project leader with id "fred" for the project "2022-1" is logged in
And the employee with id "andr" isn't in the project with id "2022-1"
When the project leader for the project "2022-1" adds the employee with id "andr" to the project
Then the employee with id "andr" is added to the project with id "2022-1"

Scenario: Add an employee to project when the project leader is not logged in
Given that there exists a project titled "Software Development" with id "2022-1"
And that the project leader for the project "2022-1" isn't logged in
And the employee with id "andr" isn't in the project with id "2022-1"
When the project leader for the project "2022-1" adds the employee with id "andr" to the project
Then the employee with id "andr" is not added to the project with id "2022-1"
And the error message "Only a project leader can add an employee to the project" is given