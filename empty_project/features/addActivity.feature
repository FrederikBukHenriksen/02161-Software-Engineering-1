#Skrevet af Frederik Buk Henriksen
Feature: Add an activity to a project
    Description: An activity is added to a project
    Actors: Project leader

    # Main scenario
    Scenario: Add activity
        Given there is a project titled "Software Development" with id "22001"
        And that the project leader is logged in
        And there is an activity titled "Analysis"
        And the activity is not on the list of activities
        When the activity is added to the list of activities
        Then the activity exists in the list of activities for the project

    # Alternative scenario:
    Scenario: Add activity when the project leader is not logged in
        Given there is a project titled "Software Development" with id "22001"
        And that the project leader is not logged in
        And there is an activity titled "Analysis"
        And the activity is not on the list of activities
        When the activity is added to the list of activities
        Then the error message "Project leader login is required" is given

    # Alternative:
    Scenario: Add activity which already exists
        Given there is a project titled "Software Development" with id "22001"
        And that the project leader is logged in
        And there is an activity titled "Analysis"
        And the activity is already on the list of activities
        When the activity is added to the list of activities
        Then the error message "Activity is already in project" is given
