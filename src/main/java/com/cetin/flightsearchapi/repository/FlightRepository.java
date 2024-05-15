package com.cetin.flightsearchapi.repository;

import com.cetin.flightsearchapi.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByDepartureAirportCityAndArrivalAirportCityAndDepartureDate(String departureAirport_city, String arrivalAirport_city, LocalDateTime departureDate);

    List<Flight> findByDepartureAirportCityAndArrivalAirportCityAndDepartureDateAndReturnDate(String departureAirport_city, String arrivalAirport_city, LocalDateTime departureDate, LocalDateTime returnDate);
}
