# Skrevet af Gustav Grøn Risager Clausen

Feature: Create a project
    sDescription: An administrator creates a project which employees can be assigned to.
    Actors: administrator

    # Main Scenario:
    Scenario: Create a new project
        Given that an administrator is logged in
        And the administrator creates a project titled "Software Development" with id "22001"
        And the project is not in the list of projects
        And the administrator sets the project start date to week "1" in the year "2022"
        And the administrator sets the project end date to week "52" in the year "2022"
        When the administrator creates the project
        Then the project titled "Software Development" with id "22001" is be added to the list of projects

    # Alternative Scenario:
    Scenario: The project is already created
        Given that an administrator is logged in
        And the administrator creates a project titled "Software Development" with id "22001"
        And the project is in the list of projects
        And the administrator sets the project start date to week "1" in the year "2022"
        And the administrator sets the project end date to week "52" in the year "2022"
        When the administrator creates the project
        Then the error message "The project already exists" is given

    # Alternative Scenario: Muhammad
    Scenario: Create a new project when the administrator is not logged in
        Given that an administrator is logged in
        And the administrator creates a project titled "Software Development" with id "22001"
        And the project is not in the list of projects
        And the administrator sets the project start date to week "1" in the year "2022"
        And the administrator sets the project end date to week "52" in the year "2022"
        When the administrator creates the project
        Then the error message "administrator login is required to create projects" i given


    # Alternative Scenario:
    Scenario: The administrator is not logged in.
        Given An administrator type "project_name" into the project name input field
        And the "project_name" is not logged in
        Then "You are not logged in as Administartor" should promt to the screen

