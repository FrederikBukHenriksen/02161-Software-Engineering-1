Feature: remove employee from an activity
    Description: remove an employee from an activity
    Actors: Project leader

    # Main use-case(s)
    Scenario: Remove an employee from an activity
        Given that the project leader is logged in
        And there is a project titled "Software Development" with id "22001"
        And the project contains an activity titled "Analysis"
        And the activity contains an employee with id "andr" in it's list of employees
        When the employee is removed from the activity's list of employees
        Then the employee with id "andr" is no longer in the activity's list of employees

    # Alternative activity
    Scenario: Remove employee from the activity but the project leader is not signed in
        Given that the project leader is not logged in
        And there is a project titled "Software Development" with id "22001"
        And the project contains an activity titled "Analysis"
        And the activity contains an employee with id "andr"
        When the employee is removed from the activity's list of employees
        Then the error message "Project leader login is required" is given




