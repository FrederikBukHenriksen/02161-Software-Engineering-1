Feature: Add and remove activity to a project
    Description: An activity is added/removed to/from a project
    Actors: Project manager

    #Main use-case(s)

    Scenario: Remove activity
        Given the project manager is logged in
        And there is a project titled "Software Development"
        And the project contains an activity titled "Analysis"
        When the activity is removed from the project
        Then the activity titled "Analysis" is no longer contained in the activity list

    # Alternative
    Scenario: Remove activity when the project manager is not logged in
        Given the project manager is not logged in
        And there is a project titled "Software Development"
        And the project contains an activity titled "Analysis"
        When the activity is removed from the project
        Then the error message "Project manager login is required" is given

