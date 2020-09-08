Feature: Free delivery for New York

  Scenario: Get departures from an airport
    Given there are scheduled departures from an airport in the next hours
    And there are flights that already departed in the past hours
    And the past and future windows were not changed
    When the client performs the action to display the departures from this airport
    Then the flights scheduled to depart in the next 4 hours are displayed
    And the STD of scheduled flights are present
    And the flights that departed in the past 2 hours are displayed
