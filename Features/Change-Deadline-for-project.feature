Feature: Set/Change Deadline for project
    Allow a project leader to set and change the deadline for a project

##Set deadline
Scenario: Set deadline for project
Given a project leader is logged in
And has chosen <project_name>
And project doesn't have a <project_end_date>
And project leaders has chosen a <project_end_date>
When the project leaders press <change_date> button
Then <project_end_date> is set

##change deadline
Scenario: change deadline for project
Given a project leader is logged in
And has chosen <project_name>
And project does have a <project_end_date>
And project leaders has chosen a new <project_end_date>
When the project leaders press <change_date> button
Then <project_end_date> is updated