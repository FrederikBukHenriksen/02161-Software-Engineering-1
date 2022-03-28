Feature: Add/remove employee
    Description: An employee is added/removed to/from the list of employees
    Actors: Administrator

    #Main use-case(s)
    Scenario: Add an employee "andr"
        Given that the administrator is logged in
        And the employee "andr" is not on the list of employees
        When the employee "andr" is added to the list of employees
        Then the employee with with id "andr" is added to the list of employees


    # #Alternative use-case(s)
    # Scenario: Add an employee when the administrator is not logged in
    #     Given that the administrator is not logged in
    #     And the employee "andr" is not on the list of employees
    #     When the employee is added to the list of employees
    #     Then the error message "Administrator login is required" is given



