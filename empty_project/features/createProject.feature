# Skrevet af Gustav Grøn Risager Clausen

Feature: Create a project
    Description: An administrator creates a project which employees can be assigned to.
    Actors: administrator

    # Main Scenario:
    Scenario: Create a new project
        Given an administrator is logged in
        And the date is year 2022 month 1 day 1
        And the project "Software Development" with id 22001 does not already exists on the list
        When create a project titled "Software Development"
        Then the project "Software Development" with id 22001 is added to the list of projects

    # Alternative Scenario:
    Scenario: The project is already created
        Given an administrator is logged in
        And the date is year 2022 month 1 day 1
        And a project "Software Development" with id 22001 already exists on the list
        When create a project titled "Software Development"
        Then the project "Software Development" with id 22001 is not added to the list of projects
        And the error message "The project with this name already exists" is given

    Scenario: Create a new project when the administrator is not logged in
        Given an administrator is not logged in
        And the date is year 2022 month 1 day 1
        And the project "Software Development" with id 22001 does not already exists on the list
        When create a project titled "Software Development"
        Then the project "Software Development" with id 22001 is not added to the list of projects
        And the error message "Administrator login is required" is given

    # Scenario: Same title but different days