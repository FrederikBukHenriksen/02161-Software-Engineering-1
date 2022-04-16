Feature: Add an employee to project
  Description: Allow a project leader to Add employees to project


Scenario: Add an employee to project
Given that there exists a project titled "Software Development" with id "2022-1"
And that the project leader is logged in
And project leader has chosen an employee with id "andr"
And the employee isn't in the project
When the project leader adds the employee to the project
Then the employee is added to the project

Scenario: Add an employee to project when the project leader is not logged in
Given that there exists a project titled "Software Development" with id "2022-1"
And that the project leader isn't logged in
And project leader has chosen an employee with id "andr"
And the employee isn't in the project
When the project leader adds the employee to the project
Then the employee is not added to the project
And the error message "Only a project leader can add an employee to the project" is given