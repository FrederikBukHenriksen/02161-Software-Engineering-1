# # Skrevet af Niklas Netterstrøm Johansen
Feature: Check an employees activities
    Description: An employee checks the activities of another employee


    # Background: createAndr
    #     Given an administrator is logged in
    #     And the employee "andr" is not on the list of employees
    #     When the employee "andr" is added to the list of employees
    #     Then the employee "andr" is on the list

    # Background: createNika
    #     Given an administrator is logged in
    #     And the employee "nika" is not on the list of employees
    #     When the employee "nika" is added to the list of employees
    #     Then the employee "nika" is on the list    
    # Main scenario
    Scenario: Check the activities assigned to another employee
        Given that employee with id "andr" is logged in
        And the employee selects another employee with id "nika"
        And the employee with id "nika" has an active activity in their list of activities
        Then the system displays the list of activities of the employee with id "nika"

#     # # Alternative scenario
    Scenario: Check the activities assigned to another employee who has no activities
        Given that employee with id "andr" is logged in
        And the employee selects another employee with id "nika"
        And the employee with id "nika" has no active activity in their list of activities
        Then the system displays that the employee with id "nika" has no active activities

