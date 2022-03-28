Feature: Add/Remove an employee from project
  Description: Allow a project leader to Add or remove employees from project


Scenario: Add an employee to project
Given that the project leader is logged in
And has chosen a project titled "Software Development" with id "22001"
And project leader hasen chosen an employee with id "andr"
And the employee isn't in the project
When the project leader adds the employee to the project
Then the employee is added to the project

Scenario: Add an employee to project when the project leader is not logged in
Given that the project leader isn't logged in
And has chosen a project titled "Software Development" with id "22001"
And project leader hasen chosen an employee with id "andr"
And the employee isn't in the project
When the project leader adds the employee to the project
Then the error message "Only a project leader can add an employee to the project" is given

Scenario: Remove an employee from the project
Given that the project leader is logged in
And has chosen a project titled "Software Development" with id "22001"
And project leader hasen chosen an employee with id "andr"
And the employee isn't in the project
When the project leader removes the employee from the project
Then the employee is removed from the project


Scenario: Remove an employee from the project when the admin isn't logged in
Given that a project leader isn't logged in
And has chosen a project titled "Software Development" with id "22001"
And project leader hasen chosen an employee with id "andr"
And the employee isn't in the project
When the project leader removes the employee from the project
Then the error message "Only a project leader can add an employee to the project" is given