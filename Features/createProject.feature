Feature: Create a project
    Description: An administrator creates a project which employees can be assigned to.
    Actors: administrator

    # Main Scenario:
    Scenario: Create a new project
        Given An administrator type <project_name> into the project name input field
        And administrator have entered <project_start_date>
        And administrator have entered <project_end_date>
        When administrator press <submit_project>
        Then the project should be added to the "calender"
    # Ikke helt sikker på at det skal tilføjes til calender

    # Alternative Scenario:
    Scenario: The project is already created
        Given An administrator type <project_name> into the project name input field
        And the <project_name> is already taken
        Then "Project name is already taken" should promt to the screen


