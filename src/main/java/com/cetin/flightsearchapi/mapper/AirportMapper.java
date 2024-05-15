package com.cetin.flightsearchapi.mapper;

import com.cetin.flightsearchapi.entity.Airport;
import com.cetin.flightsearchapi.model.response.AirportResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AirportMapper {


    // Airport nesnesini AirportDto nesnesine dönüştürmek
    public AirportResponse convertToDto(Airport airport) {
        return AirportResponse.builder()
                .id(airport.getId())
                .city(airport.getCity())
                .build();
    }

    /*public List<AirportDto> convertToDtoList(List<Airport> airports) {
        return airports.stream().map(this::convertToDto).collect(Collectors.toList());
    }*/

    public List<AirportResponse> convertToDtoList(List<Airport> airports) {
        List<AirportResponse> airportResponse = new ArrayList<>();
        for (Airport airport : airports) {
            airportResponse.add(convertToDto(airport));
        }
        return airportResponse;
    }
}
