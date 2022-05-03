#skrevet af Frederik Buk Henriksen
Feature: Add an employee to an activity
    Description: An employee is added to an activity
    Actors: Project leader

    # Main case
    Scenario: Add an employee to an activity
        Given that there exists a project titled "Software Development" with id "2022-1"
        And that the project leader with id "fred" for the project "2022-1" is logged in
        And the project with id "2022-1" contains an activity titled "Analysis"
        And there is an employee with id "andr"
        And the employee with id "andr" is not already on the activity with the title "Analysis" in the project with id "2022-1" list of employees
        When the employee with id "andr" is added to the list of employees for the activity with the title "Analysis" in the project with id "2022-1"
        Then employee with id "andr" is added to the list of employees for the activity with the title "Analysis" in the project with id "2022-1"

    # # Alternative use-case(s)  
    Scenario: Add an employee to an activity when the project leader is not logged in
        Given that there exists a project titled "Software Development" with id "2022-1"
        And that the project leader of the project "2022-1" is not logged in
        And the project with id "2022-1" contains an activity titled "Analysis"
        And there is an employee with id "andr"
        And the employee with id "andr" is not already on the activity with the title "Analysis" in the project with id "2022-1" list of employees
        When the employee with id "andr" is added to the list of employees for the activity with the title "Analysis" in the project with id "2022-1"
        Then the error message "Project leader login is required" is given

    # # Alternative use-case
    Scenario: Add an employee to an activity which already they are already assigned to
        Given that there exists a project titled "Software Development" with id "2022-1"
        And that the project leader with id "fred" for the project "2022-1" is logged in
        And the project with id "2022-1" contains an activity titled "Analysis"
        And there is an employee with id "andr"
        And the employee with id "andr" is already on the activity with the title "Analysis" in the project with id "2022-1" list of employees
        When the employee with id "andr" is added to the list of employees for the activity with the title "Analysis" in the project with id "2022-1"
        Then the error message "The employee is already assigned to the activity" is given
