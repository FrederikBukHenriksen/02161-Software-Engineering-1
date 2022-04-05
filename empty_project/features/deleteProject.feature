Feature: delete a project
    Description: An administrator deletes a project
    Actors: administrator

    # Main Scenario:
    Scenario: delete a project
        Given an administrator is logged in
        And a project "Software Development" with id "2022-1" already exists on the list
        When delete the project with id "2022-1"
        Then the project "2022-1" is not on the list of projects

    # # Alternative Scenario   Muhammad
    # Scenario: delete a project when the administrator is not logged in
    #     Given that an administrator is not logged in
    #     And has seleted project titled "Software Development" with id "22001"
    #     And the project is in the list of active projects
    #     When administrator set the status of the project to inactive
    #     Then the error message "administrator login is required"

    # # Alternative Scenario   Muhammad
    # Scenario: delete a project which doesn't exist or is inactive
    #     Given that an administrator is not logged in
    #     And has seleted project titled "Software Development" with id "22001"
    #     And the project is not in the list of active projects
    #     When administrator set the status of the project to inactive
    #     Then the error message "the project either doesn't exist or is already inactive"




