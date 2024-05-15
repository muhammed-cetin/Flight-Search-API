package com.cetin.flightsearchapi.model.response;

import com.cetin.flightsearchapi.entity.Airport;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightResponse {
    private Long id;
    private Airport departureAirport;
    private Airport arrivalAirport;
    private LocalDateTime departureDate;
    private LocalDateTime returnDate;
    private BigDecimal price;
}
