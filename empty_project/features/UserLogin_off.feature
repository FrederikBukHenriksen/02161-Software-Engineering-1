#Skrevet af Andreas Bruun
Feature: Employee logout
    Description: The user can logout of the system
    Actor: Employee

    Background: loginAndr
        Given the employee with id "andr"
        And the employee is not logged in
        And the employee's password is "01234"
        When the employee with id "andr" uses password "01234"
        Then the employee with id "andr" is logged in

    Scenario: Employee logout
        Given the employee with id "andr" is logged in
        When the employee with id "andr" logs out
        Then the employee with id "andr" is not logged in
