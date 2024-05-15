package com.cetin.flightsearchapi.model.response;

import com.cetin.flightsearchapi.entity.Airport;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReturnResponse {
    private String departureAirport;
    private String arrivalAirport;
    private LocalDateTime returnDate;
}
