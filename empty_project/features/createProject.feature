# Skrevet af Gustav Grøn Risager Clausen

Feature: Create a project
    Description: An administrator creates a project which employees can be assigned to.
    Actors: administrator

    # Main Scenario:
    Scenario: Create a new project
        Given an administrator is logged in
        And the project "Software Development" with id 0 does not already exists on the list
        When create a project titled "Software Development"
        Then the project titled "Software Development" with id 0 is be added to the list of projects

    # Alternative Scenario:
    Scenario: The project is already created
        Given an administrator is logged in
        And a project "Software Development" already exists on the list
        When create a project titled "Software Development"
        Then the project titled "Software Development" with id 1 is be added to the list of projects

    Scenario: Create a new project when the administrator is not logged in
        Given an administrator is not logged in
        And the project "Software Development" with id 0 does not already exists on the list
        When create a project titled "Software Development"
        Then the project titled "Software Development" with id 0 is not added to the list of projects
        And the error message "Administrator login is required" is given

    Scenario: Same title but different days