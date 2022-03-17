Feature: Femove worker from an activity
    Description: An employee removed from an activitiy
    Actors: Project manager

    # Main use-case(s)
    Scenario: Remove activity employee
        Given the project manager is logged in
        And there is a project titled "Software Development"
        And the project contains an activity titled "Analysis"
        And the activity contains an activitiy employee with firstName "Andreas", lastName "Bruun", and id "1"
        When the activity employee is removed from the activity employee list
        Then the activity employee with firstName "Andreas", lastName "Bruun", and id "1" is no longer conatined in the activity employee list

    # Alternative activity
    Scenario: Remove activity employee but the project manager is not signed in
        Given the project manager is logged in
        And there is a project titled "Software Development"
        And the project contains an activity titled "Analysis"
        And the activity contains an activitiy employee with firstName "Andreas", lastName "Bruun", and id "1"
        When the activity employee is removed from the activity employee list
        Then the error message "Project manager login is required" is given






