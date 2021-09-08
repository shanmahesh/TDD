#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
Feature: As a user 
  I want to Compute discount by age

  @IntegrationTest
  Scenario: Compute Discount by Age
    Given the compute disc API "/getDisc"
    When <age> is passed 
    Then the status code should be 200
    And discount should be <desc>

      Examples: 
      | age  | desc  |
      | 0    |     0 |
      | 11   |    20 |
      | 22   |    40 |
      | 42   |    60 |
      | 62   |    100|
      | 122  |    0  |
