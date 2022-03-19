Feature: Check employee activities
    Description: An employee checks the activeties of another employee
    Actors: Employee

    # Main scenario
    Scenario: Check the activeties assigned to another employee
        Given an employee selects <other_employee>
        And the selected employee has a list of activeties of positive length
        Then the system displays the list of activeties of the selected employee

    # Alternative scenario
    Scenario: The chosen employee has no assigned activeties
        Given an employee selects <other_employee>
        And the selected employee has a list of activeties of no length
        Then the system displays that the selected employee has no assigned activeties
