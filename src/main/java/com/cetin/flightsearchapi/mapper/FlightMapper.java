package com.cetin.flightsearchapi.mapper;

import com.cetin.flightsearchapi.entity.Flight;
import com.cetin.flightsearchapi.model.SearchResponse;
import com.cetin.flightsearchapi.model.response.DepartureResponse;
import com.cetin.flightsearchapi.model.response.FlightResponse;
import com.cetin.flightsearchapi.model.response.ReturnResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FlightMapper {

    // Flight nesnesini FlightDto nesnesine dönüştürmek
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

    /*public List<FlightDto> convertToFlightDtoList(List<Flight> flights) {
        return flights.stream().map(this::convertToFlightDto).collect(Collectors.toList());
    }*/

    public List<FlightResponse> convertToFlightDtoList(List<Flight> flights) {
        List<FlightResponse> flightResponse = new ArrayList<>();
        for (Flight flight : flights) {
            flightResponse.add(convertToFlightDto(flight));
        }
        return flightResponse;
    }

}
