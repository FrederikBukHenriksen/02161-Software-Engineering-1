Feature: Add/remove employee
    Description: Analysis employee is added/removed to/from the employee list
    Actors: Administrator

    #Main use-case(s)
    Scenario: Add an employee
        Given the administrator is logged in
        And there is an employee with firstName "Andreas", lastName "Bruun"
        And the employee is not on the employee list
        When the employee is added to the employee list
        Then the employee with with firstName "Andreas", lastName "Bruun", and id "1" is contained in the employee list


    #Alternative use-case(s)
    Scenario: Add an employee when the administrator is not logged in
        Given the administrator is not logged in
        And there is an employee with firstName "Andreas", lastName "Bruun"
        And the employee is not on the employee list
        When the employee is added to the employee list
        Then the error message "Administrator login is required" is given

