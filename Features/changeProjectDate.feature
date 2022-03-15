Feature: Change project start and/or end date
    Description: An administrator changes the start or end date of a project
    Actors: administrator

    # Main Scenario:
    Scenario: Change start date
        Given An administrator has seleted <project_name>
        When administrator press <change_start_date>
        Then the <project_state_date> should be changed to the selected

    # Alternative Scenario
    Scenario: Change end date
        Given An administrator has seleted <project_name>
        When administrator press <change_end_date>
        Then the <project_end_date> should be changed to the selected




