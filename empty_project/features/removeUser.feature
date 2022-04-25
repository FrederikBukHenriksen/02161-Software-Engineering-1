# Feature: Remove employee
#     Description: employee is removed from the list of employees
#     Actors: Administrator

#     # Main use-case
#     Scenario: Remove an employee
#         Given the employee "andr" exists on the list
#         And an administrator is logged in
#         When the employee "andr" is removed from the list
#         Then the employee "andr" is not on the list of employees

#     # Alternative
#     Scenario: Remove an employee when the administrator is not logged in
#         Given the employee "andr" exists on the list
#         And an administrator is not logged in
#         When the employee "andr" is removed from the list
#         Then the employee "andr" is on the list
#         And the error message "Administrator login is required" is given

#     Scenario: Remove an employee who is not on the list of employees
#         Given an administrator is not logged in
#         When the employee "fred" is removed from the list
#         Then the error message "User does not exist" is given
