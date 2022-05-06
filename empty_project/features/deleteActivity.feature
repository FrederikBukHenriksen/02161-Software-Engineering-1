Feature: Delete activity
    Description: An activity is removed from a project
    Actors: Project leader

Background: Background delete activity
        Given the date is year 2022 month 1 day 1
        And login user "HUBE"
        And create a project titled "Software Development"
        And create a project titled "Hardware Development"
        And create employee "FRED"
        And create employee "ANDR"
        And create employee "GUST"
        And set user "FRED" as project leader for project "2022-1"
        And set user "FRED" as project leader for project "2022-2"
        And login user "FRED"
        And create activity "Analysis" for project "2022-1"
        And create activity "Analysis" for project "2022-2"
        And add user "ANDR" to project "2022-1"
    
    Scenario: Remove activity
        Given login user "FRED"
        When delete activity "Analysis" from project "2022-1"
        Then the activity "Analysis" is not in the project "2022-1"     

    Scenario: Remove activity which does not exist
        Given login user "FRED"
        When delete activity "Summary" from project "2022-1"
        Then the error message "Activity does not exist in the project" is given

    Scenario: Remove activity which does not exist in the project
        Given login user "FRED"
        When delete activity "Conclusion" from project "2022-1"
        Then the error message "Activity does not exist in the project" is given

    Scenario: Remove activity not possible by administrator
        Given login user "HUBE"
        When delete activity "Analysis" from project "2022-1"
        Then the error message "Project leader login is required" is given
        And the activity "Analysis" is in the project "2022-1"      

    Scenario: Remove activity not possible by project user
        Given login user "HUBE"
        When delete activity "Analysis" from project "2022-1"
        Then the error message "Project leader login is required" is given
        And the activity "Analysis" is in the project "2022-1"    

     Scenario: Remove activity not possible by projectplanner user
        Given login user "GUST"
        When delete activity "Analysis" from project "2022-1"
        Then the error message "Project leader login is required" is given
        And the activity "Analysis" is in the project "2022-1"        




