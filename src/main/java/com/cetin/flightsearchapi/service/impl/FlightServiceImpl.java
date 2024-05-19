package com.cetin.flightsearchapi.service.impl;

import com.cetin.flightsearchapi.entity.Airport;
import com.cetin.flightsearchapi.entity.Flight;
import com.cetin.flightsearchapi.handler.exceptions.ErrorCode;
import com.cetin.flightsearchapi.handler.exceptions.ErrorDetails;
import com.cetin.flightsearchapi.handler.exceptions.GlobalException;
import com.cetin.flightsearchapi.mapper.FlightMapper;
import com.cetin.flightsearchapi.mapper.SearchMapper;
import com.cetin.flightsearchapi.model.SearchResponse;
import com.cetin.flightsearchapi.model.request.FlightRequest;
import com.cetin.flightsearchapi.model.response.FlightResponse;
import com.cetin.flightsearchapi.repository.AirportRepository;
import com.cetin.flightsearchapi.repository.FlightRepository;
import com.cetin.flightsearchapi.service.FlightService;
import jakarta.persistence.criteria.Join;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {

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
        if (flightRepository.findById(id).isPresent())
            return flightRepository.findById(id).map(flightMapper::convertToFlightDto);
        throw new GlobalException(ErrorCode.FLIGHT_NOT_FOUND);
    }

    @Override
    public FlightResponse createFlight(FlightRequest flightRequest) {

        Airport departureAirport = airportRepository.findById(flightRequest.getDepartureAirport())
                .orElseThrow(() -> new GlobalException(ErrorCode.AIRPORT_NOT_FOUND));
        Airport arrivalAirport = airportRepository.findById(flightRequest.getArrivalAirport())
                .orElseThrow(() -> new GlobalException(ErrorCode.AIRPORT_NOT_FOUND));

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
                .orElseThrow(() -> new GlobalException(ErrorCode.FLIGHT_NOT_FOUND));

        Airport departureAirport = airportRepository.findById(flightRequest.getDepartureAirport())
                .orElseThrow(() -> new GlobalException(ErrorCode.AIRPORT_NOT_FOUND));

        Airport arrivalAirport = airportRepository.findById(flightRequest.getArrivalAirport())
                .orElseThrow(() -> new GlobalException(ErrorCode.AIRPORT_NOT_FOUND));

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
        Specification<Flight> spec = buildCombinedSpecification(departureCity, arrivalCity, departureDate, returnDate);
        List<Flight> flights = flightRepository.findAll(spec);

        if (returnDate != null) {
            return searchMapper.mapToSearchResponseList(flights);
        } else {
            return searchMapper.mapToSearchResponseListOneWay(flights);
        }
    }

    private Specification<Flight> buildCombinedSpecification(String departureCity, String arrivalCity,
                                                             LocalDateTime departureDate, LocalDateTime returnDate) {
        Specification<Flight> spec = Specification.where(hasDepartureCity(departureCity))
                .and(hasArrivalCity(arrivalCity))
                .and(hasDepartureDate(departureDate));

        if (returnDate != null) {
            spec = spec.and(hasReturnDate(returnDate));
        }

        return spec;
    }


    private Specification<Flight> hasDepartureCity(String departureCity) {
        return (root, query, criteriaBuilder) -> {
            Join<Flight, Airport> departureAirportJoin = root.join("departureAirport");
            return criteriaBuilder.equal(departureAirportJoin.get("city"), departureCity);
        };
    }

    private Specification<Flight> hasArrivalCity(String arrivalCity) {
        return (root, query, criteriaBuilder) -> {
            Join<Flight, Airport> arrivalAirportJoin = root.join("arrivalAirport");
            return criteriaBuilder.equal(arrivalAirportJoin.get("city"), arrivalCity);
        };
    }

    private Specification<Flight> hasReturnDate(LocalDateTime returnDate) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("returnDate"), returnDate);
    }

    private Specification<Flight> hasDepartureDate(LocalDateTime departureDate) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("departureDate"), departureDate);
    }
}
