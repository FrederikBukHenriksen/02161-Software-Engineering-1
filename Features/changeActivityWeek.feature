Feature: Change an activity' date
    Description: Change the start or end date of an activty
    Actors: activity manager

    # Main Scenario:
    Scenario: Set start date of an activity
        Given the activity manager is logged in
        And there is a activity titled "Software Development"
        When the is set to start week "1" and year "2022"
        Then the activity is set to start week "1" and year "2022"

    Scenario: Change start date of an activity
        Given the activity manager is logged in
        And there is a activity titled "Software Development"
        And the activity start date is "1",  and year "2022"
        When the is set to start week "1", and year "2022"
        Then the activity is set to start week "1", and year "2022"

    #Alternative use-case(s)
    Scenario: Set start date of an activity when the activity manager is not logged in
        Given the activity manager is not logged in
        And there is a activity titled "Software Development"
        When the is set to start week "1", and year "2022"
        Then the error message "activity manager login is required" is given

    Scenario: Change start date of an activity when the activity manager is not logged in
        Given the activity manager is not logged in
        And there is a activity titled "Software Development"
        And the activity start date is "1", and year "2022"
        When the is set to start week "1", and year "2022"
        Then the error message "activity manager login is required" is given






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
