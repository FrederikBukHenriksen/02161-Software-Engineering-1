Feature: remove an activity from a project
    Description: An activity is added/removed to/from a project
    Actors: Project leader

Background: Background delete activity
        Given JUNIT create a project "Software Development"
        And the project "Software Development" with id "2022-1" is in the projectplanner
        And JUNIT create an employee "LEAD"
        And set user "LEAD" as project leader for project "2022-1"

    #Main use-case(s)
    Scenario: Remove activity
        Given that the project leader of the project "2022-1" is logged in
        And the activity "Analysis" is added to the project "2022-1"
        When the activity "Analysis" is removed from the project "2022-1"
        Then the activity "Analysis" is not in the project "2022-1"

    # Alternative
    Scenario: Remove activity when the project leader is not logged in
        Given JUNIT add activity "Analysis" to the project "2022-1"
        And the activity "Analysis" is in the project "2022-1"
        And that the project leader of the project "2022-1" is not logged in
        When the activity "Analysis" is removed from the project "2022-1"
        Then the error message "Project leader login is required" is given
        And the activity "Analysis" is in the project "2022-1"

    # Alternative
    Scenario: Remove activity which doesn't exist
        Given that the project leader of the project "2022-1" is logged in
        And the activity "Analysis" is not in the project "2022-1"
        When the activity "Analysis" is removed from the project "2022-1"
        Then the error message "Activity does not exist" is given
        And the activity "Analysis" is not in the project "2022-1"