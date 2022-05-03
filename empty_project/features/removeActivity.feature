Feature: remove an activity from a project
    Description: An activity is added/removed to/from a project
    Actors: Project leader

    #Main use-case(s)
    Scenario: Remove activity
        Given there is a Project titled "Software Development" with id "2022-1"
        And that the project leader with id "fred" for the project "2022-1" is logged in
        And an activity titled "Analysis" is part of the project
        And the project leader has chosen the activity
        When the activity is removed from the project
        Then the activity is no longer in the list of activities

    # Alternative
    Scenario: Remove activity when the project leader is not logged in
        Given there is a Project titled "Software Development" with id "2022-1"
        And that the Project Leader is not logged in
        And an activity titled "Analysis" is part of the project
        And the project leader has chosen the activity
        When the activity is removed from the project
        Then the error message "Project leader login is required" is given
    
    # Alternative
    Scenario: Remove activity which doesn't exist
        Given there is a Project titled "Software Development" with id "2022-1"
        And that the Project Leader is logged in
        And an activity titled "Analysis" is not part of the project
        And the project leader has chosen the activity
        When the activity is removed from the project
        Then the error message "The activity doesn't exist in the activity list" is given

