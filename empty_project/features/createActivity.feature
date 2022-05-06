#Skrevet af Frederik Buk Henriksen
Feature: Create activity
    Description: An activity is added to a project
    Actors: Project leader

    Background: Background Create activity
        Given the date is year 2022 month 1 day 1
        And login user "HUBE"
        And create a project titled "Software Development"
        And create employee "FRED"
        And create employee "ANDR"
        And set user "FRED" as project leader for project "2022-1"
        And login user "FRED"

    Scenario: Create activity
        When create activity "Analysis" for project "2022-1"
        Then the activity "Analysis" is in the project "2022-1"      

    Scenario: Create activity which already exists
        Given create activity "Analysis" for project "2022-1"
        And the activity "Analysis" is in the project "2022-1"     
        When create activity "Analysis" for project "2022-1"
        Then the error message "The activity already exists" is given
        And activity "Analysis" only appears once in  project "2022-1"   

    Scenario: Create activity when the project leader is not logged in
        Given login user "ANDR"
        When create activity "Analysis" for project "2022-1"
        Then the error message "Project leader login is required" is given
        And the activity "Analysis" is not in the project "2022-1"     
    
