Feature: Employee logout
    Description: The user can logout of the system
    Actor: Employee

    Scenario: Employee logout
        Given the employee with id "andr" is logged in
        When the employee with id "andr" logs out
        Then the employee with id "andr" is not logged in
