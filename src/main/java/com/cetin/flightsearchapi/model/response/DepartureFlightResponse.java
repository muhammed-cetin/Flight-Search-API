package com.cetin.flightsearchapi.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DepartureFlightResponse {
    private String departureAirport;
    private String arrivalAirport;
    private LocalDateTime departureDate;
}
