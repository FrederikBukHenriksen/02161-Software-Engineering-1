Feature: Create a project
    Description: An administrator creates a project which employees can be assigned to.
    Actors: administrator

    # Main Scenario:
    Scenario: Create a new project
        Given An administrator type "project_name" into the project name input field
        and the administrator is logged in.
        When administrator press "submit_project"
        Then the "project" should be added to the project Planner
    # Ikke helt sikker på at det skal tilføjes til calender

    # Alternative Scenario:
    Scenario: The project is already created
        Given An administrator type "project_name" into the project name input field
        And the "project_name" is already taken
        Then "Project name is already taken" should promt to the screen

    # Alternative Scenario:
    Scenario: The administrator is not logged in.
        Given An administrator type "project_name" into the project name input field
        And the "project_name" is not logged in
        Then "You are not logged in as Administartor" should promt to the screen

