#Skrevet af Andreas Brunn
Feature: Employee login
    Description: The user can login and logout from the site
    Actor: Employee


    #Main use-case(s)
    Scenario: Employee login
        Given the employee with id "andr"
        And the employee is not logged in
        And the employee's password is "01234"
        When the employee with id "andr" uses password "01234"
        Then the employee with id "andr" is logged in


    #Alternative use-case(s)
    Scenario: Employee has the wrong password
        Given the employee with id "andr"
        And the employee is not logged in
        And the employee's password is "01234"
        When the employee with id "andr" uses password "54321Bruun"
        Then the error message "Wrong id or password" is given
        And the employee with id "andr" is not logged in