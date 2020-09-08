package com.cinqtechnologies.demo.cucumber;

import com.cinqtechnologies.demo.client.demo.FlightInfoDisplayClient;
import com.cinqtechnologies.demo.dto.FlightInfoResponse;
import cucumber.api.CucumberOptions;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZonedDateTime;

@SpringBootTest
public class DepartureServiceStepdefs {

  private String airportCode;
  private String direction;
  private Integer pastHours;
  private Integer futureHours;

  private FlightInfoResponse response;

  @Autowired
  private FlightInfoDisplayClient client = new FlightInfoDisplayClient("http://localhost:3001");

  @Given("^there are scheduled departures from an airport in the next hours$")
  public void thereAreScheduledDeparturesFromAnAirportInTheNextHours() {
    airportCode = "AMS";
    direction = "D";
  }

  @And("^there are flights that already departed in the past hours$")
  public void thereAreFlightsThatAlreadyDepartedInThePastHours() {
    // Flifo end-to-end script should have been executed. For this demonstration the response is mocked in Mockoon
  }

  @And("^the past and future windows were not changed$")
  public void thePastAndFutureWindowsWereNotChanged() {
    pastHours = 4;
    futureHours = 2;
  }

  @When("^the client performs the action to display the departures from this airport$")
  public void theClientPerformsTheActionToDisplayTheDeparturesFromThisAirport() {
    response = client.queryService(airportCode, direction, pastHours, futureHours);
  }

  @Then("^the flights scheduled to depart in the next (\\d+) hours are displayed$")
  public void theFlightsScheduledToDepartInTheNextHoursAreDisplayed(int arg0) {
    Assert.assertNotNull(response);
    Assert.assertEquals(3, response.getFlightRecord().size());
  }

  @And("^the STD of scheduled flights are present$")
  public void theSTDOfScheduledFlightsArePresent() {
    response.getFlightRecord().stream().forEach(item -> Assert.assertNotNull(item.getScheduled()));
  }

  @And("^the flights that departed in the past (\\d+) hours are displayed$")
  public void theFlightsThatDepartedInThePastHoursAreDisplayed(int arg0) {
    response.getFlightRecord().stream().forEach(item ->  {
      ZonedDateTime date = ZonedDateTime.parse(item.getScheduled());
      Assert.assertFalse(date.isBefore(ZonedDateTime.now().minusHours(arg0)));
    });
  }
}
