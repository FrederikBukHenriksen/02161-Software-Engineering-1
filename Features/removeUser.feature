Feature: Add/remove employee
    Description: Analysis employee is added/removed to/from the employee list
    Actors: Administrator

    # Main use-case
    Scenario: Remove an employee
        Given the administrator is logged in
        And there is an employee with firstName "Andreas", lastName "Bruun"
        And the employee is on the employee list
        When the employee is removed from the employee list
        Then the employee with firstName "Andreas", lastName "Bruun", and id "1" is no longer conatined in the employee list
    # Alternative
    Scenario: Remove an employee when the administrator is not logged in
        Given the administrator is not logged in
        And there is an employee with firstName "Andreas", lastName "Bruun"
        And the employee is on the employee list
        When the employee is removed from the employee list
        Then the error message "Administrator login is required" is given
