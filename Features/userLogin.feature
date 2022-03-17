Feature: Employee login
    Description: The user can login and logout from the site
    Actor: Employee

    #Main use-case(s)
    Scenario: Employee login
        Given the employee with id "1" is not logged in
        And the employee's password is "Bruun12345"
        When the employee with id "1" uses password "Bruun12345"
        Then the employee with id "1" is logged in


    #Alternative use-case(s)
    Scenario: Employee has the wrong password
        Given the employee with id "1" is not logged in
        And the employee's password is "Bruun12345"
        When the employee with id "1" uses password "54321Bruun"
        Then the error message "Wrong id or password" is given
        Then the employee with id "1" is not logged in