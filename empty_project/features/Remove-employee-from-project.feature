#skrevet af Muhammad Muneeb Farooq
Feature: Remove an employee from project
  Description: Allow a project leader to remove employees from project

Scenario: Remove an employee from the project

Given that there exists a project titled "Software Development" with id "2022-1"
And that the project leader with id "fred" for the project "2022-1" is logged in
And project leader for the project "2022-1" has chosen an employee with id "andr"
And the employee with id "andr" is in the project with id "2022-1"
When the project leader for the project "2022-1" removes the employee with id "andr" to the project
Then the employee with id "andr" is removed from the project with id "2022-1"


# Scenario: Remove an employee from the project when the projectleader isn't logged in
# Given that a project leader isn't logged in
# And has chosen a project titled "Software Development" with id "22001"
# And project leader has chosen an employee with id "andr"
# And the employee isn't in the project
# When the project leader removes the employee from the project
# Then the error message "Only a project leader can remove an employee to the project" is given