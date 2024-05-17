package com.cetin.flightsearchapi.service;

import com.cetin.flightsearchapi.model.request.AirportRequest;
import com.cetin.flightsearchapi.model.response.AirportResponse;

import java.util.List;
import java.util.Optional;

public interface AirportService {

    List<AirportResponse> getAllAirports();

    Optional<AirportResponse> findAirportById(Long id);

    AirportResponse createAirport(AirportRequest airportRequest);

    AirportResponse updateAirport(Long id, AirportRequest airportRequest);

    void deleteAirport(Long id);
}
