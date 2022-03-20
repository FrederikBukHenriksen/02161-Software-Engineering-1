Feature: Check an employees activities
    Description: An employee checks the activities of another employee

    # Main scenario
    Scenario: Check the activities assigned to another employee
        Given that employee with id "andr" is logged in
        And the employee selects another employee with id "nika"
        And the other employee has an active activity in their list of activities
        Then the system displays the list of activities of the employee with id "nika"

    # Alternative scenario
    Scenario: Check the activities assigned to another employee who has no activities
        Given that employee with id "andr" is logged in
        And the employee selects another employee with id "nika"
        And the other employee has no active activity in their list of activities
        Then the system displays that the employee with id "nika" has no active activities
