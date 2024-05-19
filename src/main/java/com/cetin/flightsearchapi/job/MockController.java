package com.cetin.flightsearchapi.job;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mock")
public class MockController {
    private final MockFlightService mockFlightService;

    @GetMapping()
    public ResponseEntity<String> updateFlightDataFromApiManually() {
        mockFlightService.updateFlightDataFromApi();
        return ResponseEntity.ok("Scheduled job: Flight data updated successfully.");
    }
}
