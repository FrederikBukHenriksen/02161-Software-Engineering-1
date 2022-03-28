Feature: Remove employee
    Description: employee is removed from the list of employees
    Actors: Administrator

    # Main use-case
    Scenario: Remove an employee
        Given that the administrator is logged in
        And there is an employee with id "andr"
        And the employee is on the list of employees
        When the employee is removed from the list of employees
        Then the employee with id "andr" is no longer in the list of employees
    # Alternative
    Scenario: Remove an employee when the administrator is not logged in
        Given that the administrator is not logged in
        And there is an employee with id "andr"
        And the employee is on the list of employees
        When the employee is removed from the list of employees
        Then the error message "Administrator login is required" is given

    # Alternative Muhammad
    Scenario: Remove an employee who is not in the list of employees
        Given that the administrator is not logged in
        And there is an employee with id "andr"
        And the employee is not in the list of employees
        When the employee is removed from the list of employees
        Then the error message "The employee does not exist" is given
