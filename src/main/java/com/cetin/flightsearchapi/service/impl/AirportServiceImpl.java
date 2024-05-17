package com.cetin.flightsearchapi.service.impl;

import com.cetin.flightsearchapi.entity.Airport;
import com.cetin.flightsearchapi.mapper.AirportMapper;
import com.cetin.flightsearchapi.model.request.AirportRequest;
import com.cetin.flightsearchapi.model.response.AirportResponse;
import com.cetin.flightsearchapi.repository.AirportRepository;
import com.cetin.flightsearchapi.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AirportServiceImpl implements AirportService {

    private final AirportRepository airportRepository;
    private final AirportMapper airportMapper;

    @Override
    public List<AirportResponse> getAllAirports() {
        List<Airport> airports = airportRepository.findAll();
        return airportMapper.convertToAirportDtoList(airports);
    }

    @Override
    public Optional<AirportResponse> findAirportById(Long id) {
        Optional<Airport> airport = airportRepository.findById(id);
        return airport.map(airportMapper::convertToAirportDto);
    }

    @Override
    public AirportResponse createAirport(AirportRequest airportRequest) {
        Airport airport = Airport.builder()
                .city(airportRequest.getCity())
                .build();
        return airportMapper.convertToAirportDto(airportRepository.save(airport));
    }

    @Override
    public AirportResponse updateAirport(Long id, AirportRequest airportRequest) {
        Airport airport = airportRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Airport not found"));

        airport.setCity(airportRequest.getCity());

        return airportMapper.convertToAirportDto(airportRepository.save(airport));
    }

    @Override
    public void deleteAirport(Long id) {
        airportRepository.deleteById(id);
    }
}
