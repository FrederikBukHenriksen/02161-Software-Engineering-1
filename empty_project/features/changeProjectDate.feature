Feature: Change a project's start date
    Description: Change the start date of a project
    Actors: Project leader

    Background: Change a project's start date
        Given the date is year 2022 month 1 day 1
        And login user "HUBE"
        And create a project titled "Software Development"
        And create employee "FRED"
        And create employee "ANDR"
        And set user "FRED" as project leader for project "2022-1"
        And login user "FRED"

    # Main Scenario:
    Scenario: Set start date of a project
        Given login user "FRED"
        When the projectleader sets project with id "2022-1" start date to day 1, month 1, and year 2022
        Then the project with id "2022-1" start date is set to day 1, month 1, and year 2022
    
    Scenario: Set start date of a project when project leader is not logged in
        Given login user "ANDR"
        When the projectleader sets project with id "2022-1" start date to day 1, month 1, and year 2022
        Then the error message "Project leader login is required" is given

# # tænker ikke dette behøves:

# # Scenario: Change start date of a project
# #     Given that the project leader is logged in
# #     And there is a project titled "Software Development" with id "2022-1"
# #     And the project's start date is set to day "1", month "1", and year "2022"
# #     When the project's start date is set to day "1", month "12", and year "2022"
# #     Then the project's start date is set to day "1", month "12", and year "2022"

# #Alternative use-case(s)
# # Scenario: Set start date of a project when the project leader is not logged in
# #     Given that there exists a project titled "Software Development" with id "2022-1"
# #     And that the project leader of the project "2022-1" is not logged in

# #     When the projectleader  sets project with id "2022-1" start date to day "1", month "1", and year "2022"
# #     Then the error message "Project leader login is required" is given

# # tænker ikke dette behøves:

# # Scenario: Change start date of a project when the project leader is not logged in
# #     Given that the project leader is logged in
# #     And there is a project titled "Software Development" with id "2022-1"
# #     And the project's start date is set to day "1", month "1", and year "2022"
# #     When the project's start date is set to day "1", month "12", and year "2022"
# #     Then the error message "Project leader login is required" is given






# # Feature: Change project start and/or end date
# #     Description: An administrator changes the start or end date of a project
# #     Actors: administrator

# #     # Main Scenario:
# #     Scenario: Change start date
# #         Given An administrator has seleted <project_name>
# #         When administrator press <change_start_date>
# #         Then the <project_state_date> should be changed to the selected

# #     # Alternative Scenario
# #     Scenario: Change end date
# #         Given An administrator has seleted <project_name>
# #         When administrator press <change_end_date>
# #         Then the <project_end_date> should be changed to the selected
