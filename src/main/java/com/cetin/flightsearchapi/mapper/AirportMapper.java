package com.cetin.flightsearchapi.mapper;

import com.cetin.flightsearchapi.entity.Airport;
import com.cetin.flightsearchapi.model.response.AirportResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AirportMapper {

    public AirportResponse convertToAirportDto(Airport airport) {
        return AirportResponse.builder()
                .id(airport.getId())
                .city(airport.getCity())
                .build();
    }

    public List<AirportResponse> convertToAirportDtoList(List<Airport> airports) {
        List<AirportResponse> airportResponse = new ArrayList<>();
        for (Airport airport : airports) {
            airportResponse.add(convertToAirportDto(airport));
        }
        return airportResponse;
    }
}
