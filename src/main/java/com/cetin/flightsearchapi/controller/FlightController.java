package com.cetin.flightsearchapi.controller;

import com.cetin.flightsearchapi.model.SearchResponse;
import com.cetin.flightsearchapi.model.request.FlightRequest;
import com.cetin.flightsearchapi.model.response.FlightResponse;
import com.cetin.flightsearchapi.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/v1/flights")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

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

    @GetMapping("/all")
    public ResponseEntity<List<FlightResponse>> getAllFlights() {
        List<FlightResponse> flights = flightService.getFlights();
        return ResponseEntity.ok(flights);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightResponse> getFlightById(@PathVariable Long id) {
        FlightResponse flight = flightService.findFlightById(id)
                .orElseThrow(() -> new IllegalArgumentException("Flight not found"));
        return ResponseEntity.ok(flight);
    }

    @PostMapping
    public ResponseEntity<FlightResponse> createFlight(@RequestBody FlightRequest flightRequest) {
        FlightResponse createdFlight = flightService.createFlight(flightRequest);
        return ResponseEntity.ok(createdFlight);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FlightResponse> updateFlight(@PathVariable Long id, @RequestBody FlightRequest flightRequest) {
        FlightResponse updatedFlight = flightService.updateFlight(id, flightRequest);
        return ResponseEntity.ok(updatedFlight);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable Long id) {
        flightService.deleteFlight(id);
        return ResponseEntity.noContent().build();
    }

}
