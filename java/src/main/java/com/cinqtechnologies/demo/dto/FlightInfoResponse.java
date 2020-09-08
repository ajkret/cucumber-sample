package com.cinqtechnologies.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_EMPTY)
@JsonPropertyOrder({"success"})
public class FlightInfoResponse {

  private Boolean success = true;
  private String version;
  private String airportCode;
  private String airlineCode;
  private String flightNumber;
  private String adi;
  private String flightDate;
  private Boolean subscribedForUpdates;
  private List<FlightRecordDto> flightRecord;

  @Getter
  @Setter
  @JsonInclude(Include.NON_EMPTY)
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class FlightRecordDto {

    private FlightDto operatingCarrier;
    private List<FlightDto> marketingCarriers;
    private String status;
    private String statusText;
    private String scheduled;
    private String estimated;
    private String actual;
    private String duration;
    private String airportCode;
    private String cityCode;
    private String city;
    private String aircraft;
    private String tailNumber;
    private String terminal;
    private String gate;
    private String gateRemark;
    private String gateRemarkText;
    private String claim;
  }

  @Getter
  @Setter
  @JsonInclude(Include.NON_EMPTY)
  @JsonIgnoreProperties(ignoreUnknown = true)
  public static class FlightDto {

    private String airlineCode;
    private String airline;
    private String flightNumber;
  }
}
