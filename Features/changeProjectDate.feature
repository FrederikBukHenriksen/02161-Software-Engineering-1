Feature: Change a project's date
    Description: Change the start or end date of a project
    Actors: Project manager

    # Main Scenario:
    Scenario: Set start date of a project
        Given the project manager is logged in
        And there is a project titled "Software Development"
        When the is set to start day "1", month "1", and year "2022"
        Then the project is set to start day "1", month "1", and year "2022"

    Scenario: Change start date of a project
        Given the project manager is logged in
        And there is a project titled "Software Development"
        And the project start date is "1", month "1", and year "2030"
        When the is set to start day "1", month "1", and year "2022"
        Then the project is set to start day "1", month "1", and year "2022"

    #Alternative use-case(s)
    Scenario: Set start date of a project when the project manager is not logged in
        Given the project manager is not logged in
        And there is a project titled "Software Development"
        When the is set to start day "1", month "1", and year "2022"
        Then the error message "Project manager login is required" is given

    Scenario: Change start date of a project when the project manager is not logged in
        Given the project manager is not logged in
        And there is a project titled "Software Development"
        And the project start date is "1", month "1", and year "2030"
        When the is set to start day "1", month "1", and year "2022"
        Then the error message "Project manager login is required" is given






# Feature: Change project start and/or end date
#     Description: An administrator changes the start or end date of a project
#     Actors: administrator

#     # Main Scenario:
#     Scenario: Change start date
#         Given An administrator has seleted <project_name>
#         When administrator press <change_start_date>
#         Then the <project_state_date> should be changed to the selected

#     # Alternative Scenario
#     Scenario: Change end date
#         Given An administrator has seleted <project_name>
#         When administrator press <change_end_date>
#         Then the <project_end_date> should be changed to the selected
