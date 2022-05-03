# Skrevet af Gustav Grøn Risager Clausen

Feature: Create a project
    Description: An administrator creates a project which employees can be assigned to.
    Actors: administrator

    # Main Scenario:
    Scenario: Create a new project
        Given an administrator is logged in
        And the date is year 2022 month 1 day 1
        And the project "Software Development" with id "2022-1" does not already exists on the list
        When create a project titled "Software Development"
        Then the project "Software Development" with id "2022-1" is added to the list of projects

    # Alternative Scenario:
    Scenario: The project is already created
        Given an administrator is logged in
        And the date is year 2022 month 1 day 1
        And a project "Software Development" with id "2022-1" already exists on the list
        When create a project titled "Software Development"
        Then the project "Software Development" with id "2022-2" is added to the list of projects

    Scenario: Create a new project with same name but different date
        Given an administrator is logged in
        And the date is year 2022 month 1 day 1
        And a project "Software Development" with id "2022-1" already exists on the list
        When the date is year 2023 month 1 day 1
        And create a project titled "Software Development"
        Then the project "Software Development" with id "2023-2" is added to the list of projects

    Scenario: Create a new project when the administrator is not logged in
        Given an administrator is not logged in
        And the date is year 2022 month 1 day 1
        And the project "Software Development" with id "2022-1" does not already exists on the list
        When create a project titled "Software Development"
        Then the project "Software Development" with id "2022-1" is not added to the list of projects
        And the error message "Administrator login is required" is given

        

