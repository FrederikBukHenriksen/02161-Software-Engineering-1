Feature: Check employee activities
    Description: An employee checks the activeties of another employee
    Actors: Employee

    # Main scenario
    Scenario: Check the activeties assigned to another employee
        Given an employee selects <other_employee>
        And the <other_employee> has a list of activeties of positive length
        Then the system displays the list of activeties of <other_employee>

    # Alternative scenario
    Scenario: The chosen employee has no assigned activeties
        Given an employee selects <other_employee>
        And the <other_employee> has <other_employee.activeties> of no length
        Then the system displays that the <other_employee> has no assigned activeties
