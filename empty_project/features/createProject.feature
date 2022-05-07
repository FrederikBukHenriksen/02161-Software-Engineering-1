# Skrevet af Gustav Grøn Risager Clausen

Feature: Create a project
    Description: An administrator creates a project which employees can be assigned to.
    Actors: administrator

    Background: Background create project
        Given the date is year 2022 month 1 day 1
        And login user "HUBE"
        And create employee "FRED"


    # Main Scenario:
    Scenario: Create a new project
        When create a project titled "Software Development"
        Then the project "Software Development" with id "2022-1" is in the projectplanner

    # Alternative Scenario:
    Scenario: Project title is alredy used.
        And create a project titled "Software Development"
        Then the project "Software Development" with id "2022-1" is in the projectplanner
        When create a project titled "Software Development"
        Then the project "Software Development" with id "2022-2" is in the projectplanner

    Scenario: Create a new project with same name but different date
        And create a project titled "Software Development"
        And the project "Software Development" with id "2022-1" is in the projectplanner
        When the date is year 2023 month 1 day 1
        And create a project titled "Software Development"
        And the project "Software Development" with id "2023-2" is in the projectplanner

    Scenario: Create a new project when the administrator is not logged in
        Given login user "FRED"
        And an employee is logged in
        And the project "Software Development" with id "2022-1" is not in the projectplanner
        When create a project titled "Software Development"
        Then the project "Software Development" with id "2022-1" is not in the projectplanner
        And the error message "Administrator login is required" is given

        

