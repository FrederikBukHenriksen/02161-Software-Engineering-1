Feature: Delete project
    Description: Delete project from projektplanner
    Actors: administrator

    Background: Background delete project
        Given the date is year 2022 month 1 day 1
        And login user "HUBE"
        And create a project titled "Software Development"
        And create employee "FRED"
        And create employee "ANDR"
        And create employee "GUST"
        And set user "FRED" as project leader for project "2022-1"
        And login user "FRED"
        And add user "ANDR" to project "2022-1"


    Scenario: Delete project
        Given login user "HUBE"
        When delete project "2022-1" from projectplanner
        Then the project "Software Development" with id "2022-1" is not in the projectplanner

    Scenario: delete a project which does not exist
        Given login user "HUBE"
        When delete project "2022-2" from projectplanner
        Then the error message "Project does not exist" is given

    Scenario: Delete project not possible by project leader
        Given login user "FRED"
        When delete project "2022-1" from projectplanner
        Then the error message "Administrator login is required" is given
        Then the project "Software Development" with id "2022-1" is in the projectplanner

    Scenario: Delete project not possible by a project user
        Given login user "ANDR"
        When delete project "2022-1" from projectplanner
        Then the error message "Administrator login is required" is given
        Then the project "Software Development" with id "2022-1" is in the projectplanner

    Scenario: Delete project not possible by a user
        Given login user "GUST"
        When delete project "2022-1" from projectplanner
        Then the error message "Administrator login is required" is given
        Then the project "Software Development" with id "2022-1" is in the projectplanner






