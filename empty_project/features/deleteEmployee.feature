Feature: Delete employee
    Description: Delete employee from the projectplanner
    Actors: Administrator

    Background: Background delete employee
        Given login user "HUBE"
        And create employee "FRED"
        And create employee "ANDR"

    # Main use-case
    Scenario: Delete employee
        Given login user "HUBE"
        When delete user "ANDR" from the projectplanner
        And user "ANDR" is not in the projectplanner

    Scenario: Delete employee that does no exist
        Given login user "HUBE"
        When user "GUST" is not in the projectplanner
        And delete user "GUST" from the projectplanner
        Then the error message "User does not exist" is given

    Scenario: Delete employee when the administrator is not logged in
        Given login user "FRED"
        When delete user "ANDR" from the projectplanner
        Then the error message "Administrator login is required" is given
        And user "ANDR" is in the projectplanner

    Scenario: Administrator delete itself not possible
        Given login user "HUBE"
        When delete user "HUBE" from the projectplanner
        Then the error message "Cannot delete administrator profile" is given
        And user "HUBE" is in the projectplanner

