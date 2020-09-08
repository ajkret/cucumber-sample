package com.cinqtechnologies.demo.client.demo;

import com.cinqtechnologies.demo.dto.FlightInfoResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FlightInfoDisplayClient {

  private final String host;

  public FlightInfoDisplayClient(@Value("${application.fids-client.host=http://localhost:3001}") String host) {
    this.host = host;
  }

  public FlightInfoResponse queryService(final String airportCode, String direction, Integer pastHours, Integer futureHours) {
    RestTemplate restTemplate = new RestTemplate();
    return restTemplate.getForObject(String.format("%s/flifo/v3/flights/{airportCode}/{direction}?pastHours={pastHours}&futureHours={futureHours}", host),
      FlightInfoResponse.class, airportCode, direction, pastHours, futureHours);
  }
}
