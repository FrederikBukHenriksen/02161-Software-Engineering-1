Feature: Change an activity's date
    Description: Change the start/end date of an activty
    Actors: project leader

    Background: Change activity date
        Given the date is year 2022 month 1 day 1
        And login user "HUBE"
        And create a project titled "Software Development"
        And create employee "FRED"
        And create employee "ANDR"
        And create employee "GUST"
        And create employee "NIKL"

        And set user "FRED" as project leader for project "2022-1"
        And login user "FRED"
        And add user "ANDR" to project "2022-1"
        And add user "GUST" to project "2022-1"
        And create activity "Analysis" for project "2022-1"
        And add user "ANDR" to activity "Analysis" in project "2022-1"


    Scenario: Set start date of an activity
        Given login user "FRED"
        When set start week in activity "Analysis" in project "2022-1" to 19 and year 2022
        Then activity "Analysis" in project "2022-1" starts week 19 and in year 2022

    Scenario: Set start date of an activity out of week number
        Given login user "FRED"
        When set start week in activity "Analysis" in project "2022-1" to 54 and year 2022
        Then the error message "Week number not valid" is given
    
    Scenario: Set start date of an activity not possible as administrator
        Given login user "HUBE"
        When set start week in activity "Analysis" in project "2022-1" to 19 and year 2022
        Then the error message "Project leader login is required" is given

    Scenario: Set start date of an activity not possible as activity user
        Given login user "ANDR"
        When set start week in activity "Analysis" in project "2022-1" to 19 and year 2022
        Then the error message "Project leader login is required" is given

    Scenario: Set start date of an activity not possible as project user
        Given login user "GUST"
        When set start week in activity "Analysis" in project "2022-1" to 19 and year 2022
        Then the error message "Project leader login is required" is given

    Scenario: Set start date of an activity not possible as project planner user
        Given login user "NIKL"
        When set start week in activity "Analysis" in project "2022-1" to 19 and year 2022
        Then the error message "Project leader login is required" is given

    Scenario: Set start date of an activity out of week number
        Given login user "FRED"
        When set start week in activity "Analysis" in project "2022-1" to 0 and year 2022
        Then the error message "Week number not valid" is given
  
    Scenario: Set end date of an activity
        Given login user "FRED"
        When set end week in activity "Analysis" in project "2022-1" to 19 and year 2022
        Then activity "Analysis" in project "2022-1" ends week 19 and in year 2022

    Scenario: Set end date of an activity not possible as administrator
        Given login user "HUBE"
        When set end week in activity "Analysis" in project "2022-1" to 19 and year 2022
        Then the error message "Project leader login is required" is given

    Scenario: Set end date of an activity not possible as activity user
        Given login user "ANDR"
        When set end week in activity "Analysis" in project "2022-1" to 19 and year 2022
        Then the error message "Project leader login is required" is given

    Scenario: Set end date of an activity not possible as project user
        Given login user "GUST"
        When set end week in activity "Analysis" in project "2022-1" to 19 and year 2022
        Then the error message "Project leader login is required" is given

    Scenario: Set end date of an activity not possible as project user
        Given login user "NIKL"
        When set end week in activity "Analysis" in project "2022-1" to 19 and year 2022
        Then the error message "Project leader login is required" is given

    Scenario: Set start date of an activity out of week number
        Given login user "FRED"
        When set end week in activity "Analysis" in project "2022-1" to 19 and year 2022
        Then activity "Analysis" in project "2022-1" ends week 19 and in year 2022
