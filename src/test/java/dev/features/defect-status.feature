Feature: Defect Status

    Scenario: Change Status
        Given The tester is on the Home Page
        Then The tester can see only defects assigned to them
        When The tester changes the defect to any status
        Then The tester should see the defect has a different status