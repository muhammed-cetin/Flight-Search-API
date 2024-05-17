package com.cetin.flightsearchapi.mapper;

import com.cetin.flightsearchapi.entity.Airport;
import com.cetin.flightsearchapi.model.response.AirportResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AirportMapper {


    // Airport nesnesini AirportDto nesnesine dönüştürmek
    public AirportResponse convertToAirportDto(Airport airport) {
        return AirportResponse.builder()
                .id(airport.getId())
                .city(airport.getCity())
                .build();
    }

    /*public List<AirportDto> convertToAirportDtoList(List<Airport> airports) {
        return airports.stream().map(this::convertToAirportDto).collect(Collectors.toList());
    }*/

    public List<AirportResponse> convertToAirportDtoList(List<Airport> airports) {
        List<AirportResponse> airportResponse = new ArrayList<>();
        for (Airport airport : airports) {
            airportResponse.add(convertToAirportDto(airport));
        }
        return airportResponse;
    }
}
