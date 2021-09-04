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
@tag
Feature: Develop add person, list person, save person, query person 
  I want to use this template for my feature file

  @IntegrationTest
  Scenario: Register a person valid
  Given save person API is called with valid person
  When person first name "Vin" is passed
  Then the status should be 200
  And the returned person ID should be > 0
  
  @IntegrationTest
  Scenario: Register a person invalid
  Given save person API is called with invalid person
  When person first name "" is passed
  Then the status should be 400
  And the returned person ID should be 0
  
  
  
  @IntegrationTest
  Scenario: Query a person
    Given If person <prsnID> "<fstNm>" is present in the system
    When person <prsnID> is passed to the api and queried
    Then the status should be 200
    And the person name should be "<fstNm>"
		
		Examples: 
      | prsnID  | fstNm | 
      | 1 			|  Vin 	| 
      | 2 			|  Mah 	| 



  #@tag2
  #Scenario Outline: Title of your scenario outline
  #  Given I want to write a step with <name>
  #  When I check for the <value> in step
  #  Then I verify the <status> in step

  #  Examples: 
  #    | name  | value | status  |
  #    | name1 |     5 | success |
  #    | name2 |     7 | Fail    |
