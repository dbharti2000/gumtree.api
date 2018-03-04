@gumtree
Feature: Create API Tests

  @test
  Scenario: GET and POST call
    Given I create a random userId
    And I set the GET api URL
    When I make a GET request to api
    Then status code should be 200
    And response should contain newly generated id
    And id in the the response should be between 1 and 100
