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
  
	 Scenario: Person status changes
   Given The person firstNm "mah" available in the system
   When status is "" 
   And  API /statusChange is called with "Added" status 
   Then the return code should be 200 
   And persons status should be "Added"

 