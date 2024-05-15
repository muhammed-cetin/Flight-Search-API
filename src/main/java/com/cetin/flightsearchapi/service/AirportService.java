package com.cetin.flightsearchapi.service;

import com.cetin.flightsearchapi.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AirportService {
    private final AirportRepository airportRepository;

}
