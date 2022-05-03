#Skrevet af Frederik Buk Henriksen
Feature: Create activity
    Description: An activity is added to a project
    Actors: Project leader

    Background: Background Create activity
        Given JUNIT create a project "Software Development"
        And the project "Software Development" with id "2022-1" is in the projectplanner
        And JUNIT create an employee "LEAD"
        And set user "LEAD" as project leader for project "2022-1"

    Scenario: Create activity
        Given that the project leader of the project "2022-1" is logged in
        And the activity "Analysis" is not in the project "2022-1"
        When the activity "Analysis" is added to the project "2022-1"
        Then the activity "Analysis" is in the project "2022-1"

    # #Alternative use-case(s)
    Scenario: Create activity when the project leader is not logged in
        Given that the project leader of the project "2022-1" is not logged in
        And the activity "Analysis" is not in the project "2022-1"
        When the activity "Analysis" is added to the project "2022-1"
        Then the error message "Project leader login is required" is given
        And the activity "Analysis" is not in the project "2022-1"
    
    # # Alternative:
    Scenario: Create activity which already exists
        Given that the project leader of the project "2022-1" is logged in
        And the activity "Analysis" is added to the project "2022-1"
        When the activity "Analysis" is added to the project "2022-1"
        Then the error message "The activity already exists" is given
        And the activity "Analysis" only appear once in the project "2022-1"