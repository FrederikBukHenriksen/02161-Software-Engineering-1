Feature: delete a project
    Description: An administrator deletes a project
    Actors: administrator

    # Main Scenario:
    Scenario: delete a project
        Given an administrator is logged in
        And a project "Software Development" with id "2022-1" already exists on the list
        When delete the project with id "2022-1"
        Then the project "2022-1" is not on the list of projects

    # Alternative Scenario
    Scenario: delete a project when the administrator is not logged in
        Given an administrator is not logged in
        And a project "Software Development" with id "2022-1" already exists on the list
        When delete the project with id "2022-1"
        Then the project "2022-1" is on the list of projects
        And the error message "Administrator login is required" is given

    Scenario: delete a project which does not exist
        Given an administrator is not logged in
        When delete the project with id "2022-1"
        Then the error message "Project does not exist" is given




