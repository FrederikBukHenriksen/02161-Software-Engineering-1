Feature: delete project
    Description: An administrator deletes a project
    Actors: administrator

Background: Background delete project
    Given the date is year 2022 month 1 day 1


    # Main Scenario:
    Scenario: delete a project
        Given an administrator is logged in
        And create a project titled "Software Development"
        And the project "Software Development" with id "2022-1" is in the projectplanner
        When delete the project "2022-1"
        Then the project "2022-1" is not on the list of projects

    # Alternative Scenario
    Scenario: delete a project when the administrator is not logged in
        Given an administrator is not logged in
        And JUNIT create a project "Software Development"
        And the project "Software Development" with id "2022-1" is in the projectplanner
        When delete the project "2022-1"
        Then the project "Software Development" with id "2022-1" is in the projectplanner
        And the error message "Administrator login is required" is given

    Scenario: delete a project which does not exist
        Given an administrator is not logged in
        And the project "Software Development" with id "2022-1" is not in the projectplanner
        When delete the project "2022-1"
        Then the error message "Project does not exist" is given




