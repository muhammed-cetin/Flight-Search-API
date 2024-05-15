package com.cetin.flightsearchapi.service;

import com.cetin.flightsearchapi.entity.Flight;
import com.cetin.flightsearchapi.mapper.FlightMapper;
import com.cetin.flightsearchapi.mapper.SearchMapper;
import com.cetin.flightsearchapi.model.SearchResponse;
import com.cetin.flightsearchapi.model.response.FlightResponse;
import com.cetin.flightsearchapi.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;
    private final SearchMapper searchMapper;

    public List<FlightResponse> getAllFlights() {
        List<Flight> flights = flightRepository.findAll();
        return flightMapper.convertToFlightDtoList(flights);
    }

    public List<SearchResponse> searchFlights(String departureCity, String arrivalCity,
                                              LocalDateTime departureDate, LocalDateTime returnDate) {
        List<Flight> flights;
        if (returnDate == null) {
            flights = flightRepository.findByDepartureAirportCityAndArrivalAirportCityAndDepartureDate(departureCity, arrivalCity, departureDate);
            return searchMapper.mapToSearchResponseListOneWay(flights);
        } else {
            flights = flightRepository.findByDepartureAirportCityAndArrivalAirportCityAndDepartureDateAndReturnDate(departureCity, arrivalCity, departureDate, returnDate);
            return searchMapper.mapToSearchResponseList(flights);
        }
    }


}
