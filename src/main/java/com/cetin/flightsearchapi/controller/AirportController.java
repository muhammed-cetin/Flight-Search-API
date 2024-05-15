package com.cetin.flightsearchapi.controller;

import com.cetin.flightsearchapi.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/airports")
@RequiredArgsConstructor
public class AirportController {

    private final AirportService airportService;

}
