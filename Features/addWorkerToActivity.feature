Feature: Add  worker from an activity
    Description: An employee is added to an activitiy
    Actors: Project manager

    # Main case
    Scenario: Add activity employee
        Given the project manager is logged in
        And there is a project titled "Software Development"
        And the project contains an activity titled "Analysis"
        And there is an employee with firstName "Andreas", lastName "Bruun", and id "1"
        And the employee is not already on the activity's employee list
        When the activity employee is added to the activity employee list
        Then the activity employee with firstName "Andreas", lastName "Bruun", and id "1" is contained in the activity employee list

    # Alternative use-case(s)
    Scenario: Add activity employee when the project manager is not logged in
        Given the project manager is not logged in
        And there is a project titled "Software Development"
        And the project contains an activity titled "Analysis"
        And there is an employee with firstName "Andreas", lastName "Bruun", and id "1"
        And the employee is not already on the activity's employee list
        When the activity employee is added to the activity employee list
        Then the error message "Project manager login is required" is given

    # Alternative use-case
    Scenario: Add activity employee which already is assigned to the activity
        Given the project manager is logged in
        And there is a project titled "Software Development"
        And the project contains an activity titled "Analysis"
        And there is an employee with firstName "Andreas", lastName "Bruun", and id "1"
        And the employee is already on the activity's employee list
        When the activity employee is added to the activity employee list
        Then the error message "The employee is already assigned to the activity" is given
