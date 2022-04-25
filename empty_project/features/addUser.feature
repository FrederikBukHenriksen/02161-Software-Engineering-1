Feature: Add/remove employee
    Description: An employee is added/removed to/from the list of employees
    Actors: Administrator

    #Main use-case(s)
    Scenario: Add an employee "andr"
        Given an administrator is logged in
        And the employee "andr" is not on the list of employees
        When the employee "andr" is added to the list of employees
        Then the employee "andr" is on the list

    #Alternative use-case(s)
    Scenario: Add an employee when the administrator is not logged in
        Given an administrator is not logged in
        And the employee "andr" is not on the list of employees
        When the employee "andr" is added to the list of employees
        Then the employee "andr" is not on the list of employees
        And the error message "Administrator login is required" is given

    # Scenario: Add an employee when it already exists
    #     Given the employee "andr" exists on the list
    #     And that the administrator is logged in
    #     When the employee "andr" is added to the list of employees
    #     Then the employee "andr" only appears once
    #     And the error message "Initals are already in use" is given

    # Scenario: Add an employee when with more than four initials
    #     Given that the administrator is logged in
    #     And the employee "andreas" is not on the list of employees
    #     When the employee "andreas" is added to the list of employees
    #     Then the employee "andreas" is not on the list of employees
    #     And the error message "Initials must be four letters" is given

    # Scenario: Add an employee when with less than four initials
    #     Given that the administrator is logged in
    #     And the employee "and" is not on the list of employees
    #     When the employee "and" is added to the list of employees
    #     Then the employee "and" is not on the list of employees
    #     And the error message "Initials must be four letters" is given


