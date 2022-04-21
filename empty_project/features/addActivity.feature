#Skrevet af Frederik Buk Henriksen
Feature: Add an activity to a project
    Description: An activity is added to a project
    Actors: Project leader

    Scenario: Add activity
        Given that there exists a project titled "Software Development" with id "2022-1"
        And that the project leader with id "fred" for the project "2022-1" is logged in
        And there is an activity titled "Analysis"
        And the activity titled "Analysis" is not on the list of activities for the project with id "2022-1"
        When the activity titled "Analysis" is added to the list of activities for the project with id "2022-1"
        Then the activity titled "Analysis" is added to list of activities for the project with id "2022-1"

    # #Alternative use-case(s)
    Scenario: Add activity when the project leader is not logged in
        Given that there exists a project titled "Software Development" with id "2022-1"
        And that the project leader for the project "2022-1" isn't logged in
        And there is an activity titled "Analysis"
        And the activity titled "Analysis" is not on the list of activities for the project with id "2022-1"
        When the activity titled "Analysis" is added to the list of activities for the project with id "2022-1"
        Then the error message "Project leader login is required" is given
    # # Alternative:
    Scenario: Add activity which already exists
        Given that there exists a project titled "Software Development" with id "2022-1"
        And that the project leader with id "fred" for the project "2022-1" is logged in
        And there is an activity titled "Analysis"
        And the activity titled "Analysis" is already on the list of activities for the project with id "2022-1"
        When the activity titled "Analysis" is added to the list of activities for the project with id "2022-1"
        Then the error message "The activity already exists" is given