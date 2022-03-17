Feature: Employee login
    Description: The user can login on the system
    Actor: Employee


    Scenario: Employee logout
        Given the employee with id "1" is logged in
        When the employee with id "1" logs out
        Then the employee with id "1" is not logged in
