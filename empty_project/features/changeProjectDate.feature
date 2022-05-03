Feature: Change a project's start date
    Description: Change the start date of a project
    Actors: Project leader

    # Main Scenario:
    Scenario: Set start date of a project
        Given that there exists a project titled "Software Development" with id "2022-1"
        And that the project leader with id "fred" for the project "2022-1" is logged in
        When the projectleader  sets project with id "2022-1" start date to day 1, month 1, and year 2022
        Then the project's with id "2022-1" start date is set to day 1, month 1, and year 2022

# tænker ikke dette behøves:

# Scenario: Change start date of a project
#     Given that the project leader is logged in
#     And there is a project titled "Software Development" with id "2022-1"
#     And the project's start date is set to day "1", month "1", and year "2022"
#     When the project's start date is set to day "1", month "12", and year "2022"
#     Then the project's start date is set to day "1", month "12", and year "2022"

#Alternative use-case(s)
# Scenario: Set start date of a project when the project leader is not logged in
#     Given that there exists a project titled "Software Development" with id "2022-1"
#     And that the project leader of the project "2022-1" is not logged in

#     When the projectleader  sets project with id "2022-1" start date to day "1", month "1", and year "2022"
#     Then the error message "Project leader login is required" is given

# tænker ikke dette behøves:

# Scenario: Change start date of a project when the project leader is not logged in
#     Given that the project leader is logged in
#     And there is a project titled "Software Development" with id "2022-1"
#     And the project's start date is set to day "1", month "1", and year "2022"
#     When the project's start date is set to day "1", month "12", and year "2022"
#     Then the error message "Project leader login is required" is given






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
