Feature: Add/remove an activity to/from a project
    Description: An activity is added/removed to/from a project
    Actors: Project leader

    #Main use-case(s)

    Scenario: Remove activity
        Given that the project leader is logged in
        And there is a project titled "Software Development" with id "22001"
        And the project leader has chosen an activity titled "Analysis"
        And the activity is in the list of activities
        When the activity is removed from the project
        Then the activity titled "Analysis" is no longer in the list of activities

    # Alternative
    Scenario: Remove activity when the project leader is not logged in
        Given the project leader is not logged in
        And there is a project titled "Software Development" with id "22001"
        And the project leader has chosen an activity titled "Analysis"
        And the activity is in the list of activities
        When the activity is removed from the project
        Then the error message "Project leader login is required" is given
    
    # Alternative
    Scenario: Remove activity which doesn't exist
        Given the project leader is not logged in
        And there is a project titled "Software Development" with id "22001"
        And the project leader has chosen an activity titled "Analysis"
        And the activity is not in the list of activities
        When the activity is removed from the project
        Then the error message "The activity doesn't exist" is given

