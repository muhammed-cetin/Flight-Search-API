package com.cetin.flightsearchapi.controller;

import com.cetin.flightsearchapi.model.SearchResponse;
import com.cetin.flightsearchapi.model.response.FlightResponse;
import com.cetin.flightsearchapi.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/v1/flights")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    @GetMapping("/all")
    public ResponseEntity<List<FlightResponse>> getAllFlights() {
        List<FlightResponse> flights = flightService.getAllFlights();
        return ResponseEntity.ok(flights);
    }

    @GetMapping("/search")
    public ResponseEntity<List<SearchResponse>> searchFlights(
            @RequestParam String departureCity,
            @RequestParam String arrivalCity,
            @RequestParam LocalDateTime departureDate,
            @RequestParam(required = false) LocalDateTime returnDate
    ) {
        List<SearchResponse> flights = flightService.searchFlights(departureCity, arrivalCity, departureDate, returnDate);
        return ResponseEntity.ok(flights);
    }
}
