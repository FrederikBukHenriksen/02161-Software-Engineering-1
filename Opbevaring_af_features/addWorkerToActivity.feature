#skrevet af Frederik Buk Henriksen
Feature: Add an employee to an activity
    Description: An employee is added to an activity
    Actors: Project leader

    # Main case
    Scenario: Add an employee to an activity
        Given that the project leader is logged in
        And there is a project titled "Software Development" with id "22001"
        And the project contains an activity titled "Analysis"
        And there is an employee with id "andr"
        And the employee is not already on the activity's list of employees
        When the employee is added to the list of employees for the activity
        Then the employee with id "andr" is added to list of employees for the activity

    # Alternative use-case(s)
    Scenario: Add an employee to an activity when the project leader is not logged in
        Given that the project leader is not logged in
        And there is a project titled "Software Development" with id "22001"
        And the project contains an activity titled "Analysis"
        And there is an employee with id "andr"
        And the employee is not already on the activity's list of employees
        When the employee is added to the activity's list of employees
        Then the error message "Project leader login is required" is given

    # Alternative use-case
    Scenario: Add an employee to an activity which already they are already assigned to
        Given that the project leader is logged in
        And there is a project titled "Software Development" with id "22001"
        And the project contains an activity titled "Analysis"
        And there is an employee with id "andr"
        And the employee is already on the activity's list of employees
        When the employee is added to the activity's list of employees
        Then the error message "The employee is already assigned to the activity" is given
