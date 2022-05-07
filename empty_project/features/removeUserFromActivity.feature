Feature: Remove employee from an activity
    Description: remove an employee from an activity
    Actors: Project leader

Background: Background remove employee from an activity
    Given the date is year 2022 month 1 day 1
    And login user "HUBE"
    And create a project titled "Software Development"
    And create employee "FRED"
    And create employee "ANDR"
    And create employee "GUST"
    And create employee "NIKL"
    And set user "FRED" as project leader for project "2022-1"
    And login user "FRED"
    And add user "FRED" to project "2022-1"
    And add user "ANDR" to project "2022-1"
    And add user "GUST" to project "2022-1"
    And create activity "Analysis" for project "2022-1"

    Scenario: Remove an employee from an activity
        Given login user "FRED"
        When remove user "ANDR" from activity "Analysis" project "2022-1"
        Then user "ANDR" is not in activity "Analysis" in project "2022-1"

    Scenario: Remove an employee from an activity which is not in the activity
        Given login user "FRED"
        When remove user "NIKL" from activity "Analysis" project "2022-1"
        Then the error message "User is not in the activity" is given
        And user "ANDR" is not in activity "Analysis" in project "2022-1"

    Scenario: 


    #     Given that the project leader is logged in
    #     And there is a project titled "Software Development" with id "22001"
    #     And the project contains an activity titled "Analysis"
    #     And the activity contains an employee with id "andr" in it's list of employees
    #     When the employee is removed from the activity's list of employees
    #     Then the employee with id "andr" is no longer in the activity's list of employees

    # # Alternative activity
    # Scenario: Remove employee from the activity but the project leader is not signed in
    #     Given that the project leader is not logged in
    #     And there is a project titled "Software Development" with id "22001"
    #     And the project contains an activity titled "Analysis"
    #     And the activity contains an employee with id "andr"
    #     When the employee is removed from the activity's list of employees
    #     Then the error message "Project leader login is required" is given




