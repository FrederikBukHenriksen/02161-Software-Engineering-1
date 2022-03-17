Feature: Add an activity to a project
    Description: An activity is added to a project
    Actors: Project manager

    Scenario: Add activity
        Given the project manager is logged in
        And there is a project titled "Software Development"
        And there is an activity titled "Analysis"
        And the activity is not on the activity list
        When the activity is added to the activity list
        Then the project titled "Software Development" contains an activity titled "Analysis"

    #Alternative use-case(s)
    Scenario: Add activity when the project manager is not logged in
        Given the project manager is not logged in
        And there is a project titled "Software Development"
        And there is an activity titled "Analysis"
        And the activity is not on the activity list
        When the activity is added to the activity list
        Then the error message "Project manager login is required" is given
    # Alternative:
    Scenario: Add activity which already exists
        Given the project manager is logged in
        And there is a project titled "Software Development"
        And there is an activity titled "Analysis"
        And the activity is on the activity list
        When the activity is added to the activity list
        Then the error message "The activity already exists" is given