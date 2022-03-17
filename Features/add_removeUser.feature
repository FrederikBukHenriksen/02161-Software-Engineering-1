Feature: Add/remove User
    Description: A user is added/removed to memberList
    Actors: Administrator

Scenario: Add a user successfully
    Given that the administrator is logged in
    And there is a user with firstName "Andreas", lastName "Bruun", and IDnum "213432"
    And the user is not on the memberList
    When the user is added to the memberList
    Then the user with with firstName "Andreas", lastName "Bruun", and IDnum "213432" is contained in the memberList
    
Scenario: Add a user when the administrator is not logged in
    Given that the administrator is not logged in
    And there is a user with firstName "Andreas", lastName "Bruun", and IDnum "213432"
    When the user is added to the memberList
    Then the error message "Administrator login required" is given

Scenario: Remove a user successfully
    Given that the administrator is logged in
    And there is a user with firstName "Andreas", lastName "Bruun", and IDnum "213432"
    And the user is on the memberList
    When the user is removed from the memberList
    Then the user with firstName "Andreas", lastName "Bruun", and IDnum "213432" is removed from the memberList


Scenario: Remove a user when the administrator is not logged in
    Given that the administrator is not logged in
    And there is a user with firstName "Andreas", lastName "Bruun", and IDnum "213432"
    When the user is removed from the memberList
    Then the error message "Administrator login required" is given
