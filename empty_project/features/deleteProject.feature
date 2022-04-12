Feature: delete a project
    Description: An administrator deletes a project
    Actors: administrator

    # Main Scenario:
    Scenario: delete a project
        Given an administrator is logged in
        And the date is year 2022 month 1 day 1
        And a project "Software Development" with id 22001 already exists on the list
        When delete the project with id 22001
        Then the project 22001 is not on the list of projects

    # # Alternative Scenario
    # Scenario: delete a project when the administrator is not logged in
    #     Given an administrator is not logged in
    #     And the date is year 2022 month 1 day 1
    #     And a project "Software Development" with id 22001 already exists on the list
    #     When delete the project with id 22001
    #     Then the project 22001 is on the list of projects
    #     And the error message "activity leader login is required" is given

    # Scenario: delete a project which doesn't exist or is inactive
    #     Given that an administrator is not logged in
    #     And has seleted project titled "Software Development" with id "22001"
    #     And the project is not in the list of active projects
    #     When administrator set the status of the project to inactive
    #     Then the error message "the project either doesn't exist or is already inactive"




