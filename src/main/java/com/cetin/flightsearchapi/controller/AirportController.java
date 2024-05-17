package com.cetin.flightsearchapi.controller;

import com.cetin.flightsearchapi.model.request.AirportRequest;
import com.cetin.flightsearchapi.model.response.AirportResponse;
import com.cetin.flightsearchapi.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/airports")
@RequiredArgsConstructor
public class AirportController {

    private final AirportService airportService;

    @GetMapping("/all")
    public ResponseEntity<List<AirportResponse>> getAllAirports() {
        return ResponseEntity.ok(airportService.getAllAirports());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirportResponse> getAirportById(@PathVariable Long id) {
        return ResponseEntity.ok(airportService.findAirportById(id)
                .orElseThrow(() -> new IllegalArgumentException("Airport not found")));
    }

    @PostMapping
    public ResponseEntity<AirportResponse> createAirport(@RequestBody AirportRequest airportRequest) {
        return ResponseEntity.ok(airportService.createAirport(airportRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AirportResponse> updateAirport(@PathVariable Long id, @RequestBody AirportRequest airportRequest) {
        return ResponseEntity.ok(airportService.updateAirport(id, airportRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAirport(@PathVariable Long id) {
        airportService.deleteAirport(id);
        return ResponseEntity.noContent().build();
    }


}
