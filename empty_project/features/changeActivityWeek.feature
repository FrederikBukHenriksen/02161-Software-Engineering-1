#Skrevet af Gustav Grøn Risager Clausen
Feature: Change an activity' date
    Description: Change the start/end date of an activty
    Actors: project leader

    # Main Scenario:
    Scenario: Set start date of an activity
        Given that there exists a project titled "Software Development" with id "2022-1"
        And that the project leader with id "fred" for the project "2022-1" is logged in
        And the project with id "2022-1" contains an activity titled "Analysis"
        When the project "2022-1" activity's with title "Analysis" start date is set to week 1 and year 2022
        Then the project "2022-1" activity's with title "Analysis" date is set to week 1 and year 2022

    # Scenario: Change start date of an activity
    #     Given that the project leader is logged in
    #     And there is a project titled "Software Development" with id "2022-1"
    #     And the project contains an activity titled "Analysis"
    #     And the start date of activity is set to week 1,  and year 2022
    #     When the activity's new start date is set to week 2, and year 2022
    #     Then the activity's start date is set to week 2, and year 2022

    # Alternative use-case(s)
    Scenario: Set start date of an activity when the project leader is not logged in
        Given that there exists a project titled "Software Development" with id "2022-1"
        And that the project leader for the project "2022-1" isn't logged in
        And the project with id "2022-1" contains an activity titled "Analysis"
        When the project "2022-1" activity's with title "Analysis" start date is set to week 1 and year 2022
        Then the error message "Project leader login is required" is given

# Scenario: Change start date of an activity when the project leader is not logged in
#     Given that the project leader is not logged in
#     And there is a project titled "Software Development" with id "2022-1"
#     And the project contains an activity titled "Analysis"
#     And the activity's start date is set to week 1, and year 2022
#     When the activity's new start date is set to week 2, and year 2022
#     Then the error message "activity leader login is required" is given






# Feature: Change activity start and/or end date
#     Description: An administrator changes the start or end date of a activity
#     Actors: administrator

#     # Main Scenario:
#     Scenario: Change start date
#         Given An administrator has seleted <activity_name>
#         When administrator press <change_start_date>
#         Then the <activity_state_date> should be changed to the selected

#     # Alternative Scenario
#     Scenario: Change end date
#         Given An administrator has seleted <activity_name>
#         When administrator press <change_end_date>
#         Then the <activity_end_date> should be changed to the selected
