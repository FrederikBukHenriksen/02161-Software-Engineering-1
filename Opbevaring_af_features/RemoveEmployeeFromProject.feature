#skrevet af Muhammad Muneeb Farooq
Feature: Remove employee from project
  Description: Allow a project leader to remove employees from project

Background: Background remove employee from project
Given that there exists a project titled "Software Development" with id "2022-1"
And JUNIT create an employee "fred"
And JUNIT create an employee "andr"
And set user "fred" as project leader for project "2022-1"


Scenario: Remove an employee from the project
Given that the project leader of the project "2022-1" is logged in
And the user "andr" is added to the project "2022-1"
When the user "andr" is removed from the project "2022-1"
And the user "andr" is not in the project "2022-1"


# Scenario: Remove an emploee which is not assigned to the project






Scenario: Remove an employee from the project when the projectleader isn't logged in
Given that the project leader of the project "2022-1" is not logged in
And JUNIT Add user "andr" is to project "2022-1"
And the user "andr" is in the project "2022-1"
When the user "andr" is removed from the project "2022-1"
Then the error message "Project leader login is required" is given
And the user "andr" is in the project "2022-1"




# And has chosen a project titled "Software Development" with id "22001"
# And project leader has chosen an employee with id "andr"
# And the employee isn't in the project
# When the project leader removes the employee from the project
# Then the error message "Only a project leader can remove an employee to the project" is given