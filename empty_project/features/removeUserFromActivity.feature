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
    And add user "FRED" to activity "Analysis" in project "2022-1"
    And add user "ANDR" to activity "Analysis" in project "2022-1"
    And add user "GUST" to activity "Analysis" in project "2022-1"


    And create activity "Analysis" for project "2022-1"

    Scenario: Remove an employee from an activity
        Given login user "FRED"
        When remove user "ANDR" from activity "Analysis" project "2022-1"
        Then user "ANDR" is not in activity "Analysis" in project "2022-1"

    Scenario: Project leader removes itself from activity
        Given login user "FRED"
        When remove user "FRED" from activity "Analysis" project "2022-1"
        Then user "FRED" is not in activity "Analysis" in project "2022-1"  

    Scenario: Remove an employee from an activity which is not in the activity
        Given login user "FRED"
        When remove user "NIKL" from activity "Analysis" project "2022-1"
        Then the error message "User is not in the activity" is given

    Scenario: Remove an employee which does not exist
        Given login user "FRED"
        When remove user "MUHA" from activity "Analysis" project "2022-1"
        Then the error message "User does not exist" is given

    Scenario: Remove an employee from an activity which does not exist
        Given login user "FRED"
        When remove user "ANDR" from activity "Conclusion" project "2022-1"
        Then the error message "Activity does not exist in the project" is given   
    
    Scenario: Remove an employee from an activity not possible when administrator is logged in
        Given login user "HUBE"
        When remove user "ANDR" from activity "Analysis" project "2022-1"
        Then the error message "Project leader login is required" is given   
        And user "ANDR" is in activity "Analysis" in project "2022-1"

    Scenario: Remove an employee from an activity not possible when activity user is logged in
        Given login user "ANDR"
        When remove user "GUST" from activity "Analysis" project "2022-1"
        Then the error message "Project leader login is required" is given   
        And user "GUST" is in activity "Analysis" in project "2022-1"

    Scenario: Remove an employee from an activity not possible when projectplanner user is logged in
        Given login user "NIKL"
        When remove user "ANDR" from activity "Analysis" project "2022-1"
        Then the error message "Project leader login is required" is given   
        And user "ANDR" is in activity "Analysis" in project "2022-1"



