package com.cetin.flightsearchapi.mapper;

import com.cetin.flightsearchapi.entity.Flight;
import com.cetin.flightsearchapi.model.response.FlightResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FlightMapper {

    public FlightResponse convertToFlightDto(Flight flight) {
        return FlightResponse.builder()
                .id(flight.getId())
                .departureAirport(flight.getDepartureAirport())
                .arrivalAirport(flight.getArrivalAirport())
                .departureDate(flight.getDepartureDate())
                .returnDate(flight.getReturnDate())
                .price(flight.getPrice())
                .build();
    }

    public List<FlightResponse> convertToFlightDtoList(List<Flight> flights) {
        List<FlightResponse> flightResponse = new ArrayList<>();
        for (Flight flight : flights) {
            flightResponse.add(convertToFlightDto(flight));
        }
        return flightResponse;
    }
}
