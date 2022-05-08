#Skrevet af Gustav Grøn Risager Clausen
Feature: Change an activity' date
    Description: Change the start/end date of an activty
    Actors: project leader

    Background: Background Change activity'date
        Given the date is year 2022 month 1 day 1
        And login user "HUBE"
        And create a project titled "Software Development"
        And create employee "FRED"
        And create employee "ANDR"
        And set user "FRED" as project leader for project "2022-1"
        And login user "FRED"
        And create activity "Analysis" for project "2022-1"
        And add user "ANDR" to the project "2022-1"
        And add user "ANDR" to activity "Analysis" for project "2022-1"


    # Main Scenario:
    Scenario: Set start date of an activity
        Given login user "FRED"
        When the project "2022-1" activity's with title "Analysis" start date is set to week 1 and year 2022
        Then the project "2022-1" activity's with title "Analysis" date is set to week 1 and year 2022



    # Alternative use-case(s)
    Scenario: Set start date of an activity when the project leader is not logged in
        Given that there exists a project titled "Software Development" with id "2022-1"
        And that the project leader of the project "2022-1" is not logged in
        And the project with id "2022-1" contains an activity titled "Analysis"
        When the project "2022-1" activity's with title "Analysis" start date is set to week 1 and year 2022
        Then the error message "Project leader login is required" is given

