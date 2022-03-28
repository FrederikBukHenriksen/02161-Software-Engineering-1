# Skrevet af Andreas Bruun

Feature: Add/remove employee
    Description: An employee is added/removed to/from the list of employees
    Actors: Administrator

    #Main use-case(s)
    Scenario: Add an employee
        Given that the administrator is logged in
        And there is an employee with id "andr"
        And the employee is not on the list of employees
        When the employee is added to the list of employees
        Then the employee with with id "andr" is added to the list of employees


    #Alternative use-case(s)
    Scenario: Add an employee when the administrator is not logged in
        Given that the administrator is not logged in
        And there is an employee with id "andr"
        And the employee is not on the list of employees
        When the employee is added to the list of employees
        Then the error message "Administrator login is required" is given

    #Alternative use-case(s)
    Scenario: Add an employee when they already exist
        Given that the administrator is not logged in
        And there is an employee with id "andr"
        And the employee is on the list of employees
        When the employee is added to the list of employees
        Then the error message "An employee with this id already exists" is given

