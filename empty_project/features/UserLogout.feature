Feature: User logout
    Description: User logut
    Actor: Administrator and employee

    Background: Background user logout 
        Given the date is year 2022 month 1 day 1
        And login user "HUBE"
        And create employee "ANDR"

    # Scenario: User logout
    #     Given login user "ANDR"
    #     When logout
    #     Then no user is logged in.

    # Scenario: No user is logged in upon logout
    #     Given logout
    #     And no user is logged in.
    #     When logout
    #     Then no user is logged in.
