package com.cetin.flightsearchapi.service.impl;

import com.cetin.flightsearchapi.entity.Airport;
import com.cetin.flightsearchapi.entity.Flight;
import com.cetin.flightsearchapi.mapper.FlightMapper;
import com.cetin.flightsearchapi.mapper.SearchMapper;
import com.cetin.flightsearchapi.model.SearchResponse;
import com.cetin.flightsearchapi.model.request.FlightRequest;
import com.cetin.flightsearchapi.model.response.FlightResponse;
import com.cetin.flightsearchapi.repository.AirportRepository;
import com.cetin.flightsearchapi.repository.FlightRepository;
import com.cetin.flightsearchapi.service.FlightService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {

    @PersistenceContext
    private final EntityManager entityManager;
    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;
    private final FlightMapper flightMapper;
    private final SearchMapper searchMapper;

    @Override
    public List<FlightResponse> getFlights() {
        List<Flight> flights = flightRepository.findAll();
        return flightMapper.convertToFlightDtoList(flights);
    }

    @Override
    public Optional<FlightResponse> findFlightById(Long id) {
        Optional<Flight> flight = flightRepository.findById(id);
        return flight.map(flightMapper::convertToFlightDto);
    }

    @Override
    public FlightResponse createFlight(FlightRequest flightRequest) {
        Airport departureAirport = airportRepository.findById(flightRequest.getDepartureAirport())
                .orElseThrow(() -> new IllegalArgumentException("Departure airport not found"));
        Airport arrivalAirport = airportRepository.findById(flightRequest.getArrivalAirport())
                .orElseThrow(() -> new IllegalArgumentException("Arrival airport not found"));

        Flight flight = Flight.builder()
                .departureAirport(departureAirport)
                .arrivalAirport(arrivalAirport)
                .departureDate(flightRequest.getDepartureDate())
                .returnDate(flightRequest.getReturnDate())
                .price(flightRequest.getPrice())
                .build();

        return flightMapper.convertToFlightDto(flightRepository.save(flight));
    }

    @Override
    public FlightResponse updateFlight(Long id, FlightRequest flightRequest) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Flight not found"));

        Airport departureAirport = airportRepository.findById(flightRequest.getDepartureAirport())
                .orElseThrow(() -> new IllegalArgumentException("Departure airport not found"));

        Airport arrivalAirport = airportRepository.findById(flightRequest.getArrivalAirport())
                .orElseThrow(() -> new IllegalArgumentException("Arrival airport not found"));

        flight.setDepartureAirport(departureAirport);
        flight.setArrivalAirport(arrivalAirport);
        flight.setDepartureDate(flightRequest.getDepartureDate());
        flight.setReturnDate(flightRequest.getReturnDate());
        flight.setPrice(flightRequest.getPrice());

        return flightMapper.convertToFlightDto(flightRepository.save(flight));
    }

    @Override
    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }

    @Override
    public List<SearchResponse> searchFlights(String departureCity, String arrivalCity,
                                              LocalDateTime departureDate, LocalDateTime returnDate) {
        List<Flight> flights;

        if (returnDate == null) {
            flights = entityManager.createQuery(
                            "SELECT f FROM Flight f WHERE f.departureAirport.city = :departureCity AND f.arrivalAirport.city = :arrivalCity AND f.departureDate = :departureDate",
                            Flight.class)
                    .setParameter("departureCity", departureCity)
                    .setParameter("arrivalCity", arrivalCity)
                    .setParameter("departureDate", departureDate)
                    .getResultList();
            return searchMapper.mapToSearchResponseListOneWay(flights);
        } else {
            flights = entityManager.createQuery(
                            "SELECT f FROM Flight f WHERE f.departureAirport.city = :departureCity AND f.arrivalAirport.city = :arrivalCity AND f.departureDate = :departureDate AND f.returnDate = :returnDate",
                            Flight.class)
                    .setParameter("departureCity", departureCity)
                    .setParameter("arrivalCity", arrivalCity)
                    .setParameter("departureDate", departureDate)
                    .setParameter("returnDate", returnDate)
                    .getResultList();
            return searchMapper.mapToSearchResponseList(flights);
        }
    }
}
