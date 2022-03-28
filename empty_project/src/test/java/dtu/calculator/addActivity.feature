Feature: Add an activity to a project
    Description: An activity is added to a project
    Actors: Project leader

    Scenario: Add activity
        Given that the project leader is logged in
        And there is a project titled "Software Development" with id "22001"
        And there is an activity titled "Analysis"
        And the activity is not on the list of activities
        When the activity is added to the list of activities
        Then the activity is added to list of activities for the project

    #Alternative use-case(s)
    Scenario: Add activity when the project leader is not logged in
        Given that the project leader is not logged in
        And there is a project titled "Software Development" with id "22001"
        And there is an activity titled "Analysis"
        And the activity is not on the list of activities
        When the activity is added to the list of activities
        Then the error message "Project leader login is required" is given
    # Alternative:
    Scenario: Add activity which already exists
        Given that the project leader is logged in
        And there is a project titled "Software Development" with id "22001"
        And there is an activity titled "Analysis"
        And the activity is already on the list of activities
        When the activity is added to the list of activities
        Then the error message "The activity already exists" is given