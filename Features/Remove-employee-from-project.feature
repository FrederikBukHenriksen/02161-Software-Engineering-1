Feature: Add/Remove employee from project
    Allow a project leader to Add or remove employees from project


Scenario: Add employee to project
Given that a project leader is logged in
And has chosen <project_name>
And project leader hasen chosen <employee_name>
And <employee_name> isn't in <project_name>

Scenario: Add employee to project when the project leader is not logged in
Given that a project leader isn't logged in
And has chosen <project_name>
And project leader hasen chosen <employee_name>
And <employee_name> isn't in <project_name>
When the project leaders press <add_employee> button
Then the error message "Only a project leader can add an employee to the project" is given

Scenario: Remove employee from the project
Given that a project leader is logged in
And has chosen <project_name>
And project leader hasen chosen <employee_name>
And <employee_name> is in <project_name>
When the project leaders presses the <remove_employee> buttonx
Then <employee_name> is removed from <project_name>
Given that the administrator is not logged in
Then the error message "Only a project leader can add an employee to the project" is given

Scenario: Remove employee from the project when the admin isn't logged in
Given that a project leader isn't logged in
And has chosen <project_name>
And project leader hasen chosen <employee_name>
And <employee_name> is in <project_name>
When the project leaders presses the <remove_employee> buttonx
Then the error message "Only a project leader can add an employee to the project" is given