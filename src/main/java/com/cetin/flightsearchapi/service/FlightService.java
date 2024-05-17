package com.cetin.flightsearchapi.service;

import com.cetin.flightsearchapi.model.SearchResponse;
import com.cetin.flightsearchapi.model.request.FlightRequest;
import com.cetin.flightsearchapi.model.response.FlightResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface FlightService {

    List<FlightResponse> getFlights();

    Optional<FlightResponse> findFlightById(Long id);

    FlightResponse createFlight(FlightRequest flightRequest);

    FlightResponse updateFlight(Long id, FlightRequest flightRequest);

    void deleteFlight(Long id);

    List<SearchResponse> searchFlights(String departureCity, String arrivalCity,
                                       LocalDateTime departureDate, LocalDateTime returnDate);
}
