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
Feature: feature to test login functionality of project1

  #Scenario Outline for paramatized tests
  #<> adds paramtized options
  @project1
  Scenario Outline: check login is succesful with valid credentials of project1
    Given new tab is open
    And user is on login page of project1
    When user enters <username> and <password>
    And user clicks on login button of project1
    Then user is navigated to the home page of project1

    Examples: |
      | username    | password |
      | 'aardiente' | 'tester' |
