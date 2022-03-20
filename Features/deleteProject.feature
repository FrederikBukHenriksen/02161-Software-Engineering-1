Feature: Create a project
    Description: An administrator deletes a project
    Actors: administrator

    # Main Scenario:
    Scenario: Create a new project
        Given An administrator has seleted "project_name"
        When administrator press "disable_project"
        Then the "project_status" should be changed to "inactive"

    # Alternative Scenario:
    Scenario: Create a new project
        Given An administrator is not logged in
        and seleted "project_name"
        When administrator press "disable_project"
        Then the an error message should promt: "you are not logged in"





