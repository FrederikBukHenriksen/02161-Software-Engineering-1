Feature: Change an activity's date
    Description: Change the start/end date of an activty
    Actors: project leader

  Background: Change activity date
    Given the date is year 2022 month 1 day 1
    And login user "HUBE"
    And create a project titled "Software Development"
    And create employee "FRED"
    And set user "FRED" as project leader for project "2022-1"
    And login user "FRED"
    And create activity "Analysis" for project "2022-1"

  Scenario: Set start date of an activity
    Given login user "FRED"
    When set start week in activity "Analysis" in project "2022-1" to 19 and year 2022
    Then activity "Analysis" in project "2022-1" starts week 19 and in year 2022

  Scenario: Set start date of an activity out of week number
    Given login user "FRED"
    When set start week in activity "Analysis" in project "2022-1" to 54 and year 2022
    Then activity "Analysis" in project "2022-1" starts week 19 and in year 2022



#         Given that there exists a project titled "Software Development" with id "2022-1"
#         And the user "fred" for the project "2022-1" is logged in
#         And the project with id "2022-1" contains an activity titled "Analysis"
#         When the project "2022-1" activity's with title "Analysis" start date is set to week 1 and year 2022
#         Then the project "2022-1" activity's with title "Analysis" date is set to week 1 and year 2022
    
#     # Scenario: Change start date of an activity
#     #     Given that the project leader is logged in
#     #     And there is a project titled "Software Development" with id "2022-1"
#     #     And the project contains an activity titled "Analysis"
#     #     And the start date of activity is set to week 1,  and year 2022
#     #     When the activity's new start date is set to week 2, and year 2022
#     #     Then the activity's start date is set to week 2, and year 2022

#     # Alternative use-case(s)
#     Scenario: Set start date of an activity when the project leader is not logged in
#         Given that there exists a project titled "Software Development" with id "2022-1"
#         And that the project leader of the project "2022-1" is not logged in
#         And the project with id "2022-1" contains an activity titled "Analysis"
#         When the project "2022-1" activity's with title "Analysis" start date is set to week 1 and year 2022
#         Then the error message "Project leader login is required" is given

# # Scenario: Change start date of an activity when the project leader is not logged in
# #     Given that the project leader is not logged in
# #     And there is a project titled "Software Development" with id "2022-1"
# #     And the project contains an activity titled "Analysis"
# #     And the activity's start date is set to week 1, and year 2022
# #     When the activity's new start date is set to week 2, and year 2022
# #     Then the error message "activity leader login is required" is given






# # Feature: Change activity start and/or end date
# #     Description: An administrator changes the start or end date of a activity
# #     Actors: administrator

# #     # Main Scenario:
# #     Scenario: Change start date
# #         Given An administrator has seleted <activity_name>
# #         When administrator press <change_start_date>
# #         Then the <activity_state_date> should be changed to the selected

# #     # Alternative Scenario
# #     Scenario: Change end date
# #         Given An administrator has seleted <activity_name>
# #         When administrator press <change_end_date>
# #         Then the <activity_end_date> should be changed to the selected
