package com.cetin.flightsearchapi.mapper;

import com.cetin.flightsearchapi.entity.Flight;
import com.cetin.flightsearchapi.model.SearchResponse;
import com.cetin.flightsearchapi.model.response.DepartureResponse;
import com.cetin.flightsearchapi.model.response.ReturnResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SearchMapper {

    private DepartureResponse mapToDepartureResponse(Flight flight) {
        return DepartureResponse.builder()
                .departureAirport(flight.getDepartureAirport().getCity())
                .arrivalAirport(flight.getArrivalAirport().getCity())
                .departureDate(flight.getDepartureDate())
                .build();
    }

    // Flight nesnesini ReturnResponse nesnesine dönüştürmek flight.getDepartureAirport().getCity()
    private ReturnResponse mapToReturnResponse(Flight flight) {
        return ReturnResponse.builder()
                .departureAirport(flight.getArrivalAirport().getCity())
                .arrivalAirport(flight.getDepartureAirport().getCity())
                .returnDate(flight.getReturnDate())
                .build();
    }

    // Flight nesnesini SearchResponse nesnesine dönüştürmek (çift yönlü)
    public SearchResponse mapToSearchResponse(Flight flight) {
        return SearchResponse.builder()
                .id(flight.getId())
                .round(mapToDepartureResponse(flight))
                .trip(mapToReturnResponse(flight))
                .price(flight.getPrice())
                .build();
    }

    // Flight nesnesini SearchResponse nesnesine dönüştürmek
    public SearchResponse mapToSearchResponseOneWay(Flight flight) {
        return SearchResponse.builder()
                .id(flight.getId())
                .round(mapToDepartureResponse(flight))
                .price(flight.getPrice())
                .build();
    }

    // Flight listesini SearchResponse listesine dönüştürmek
    public List<SearchResponse> mapToSearchResponseList(List<Flight> flights) {
        return flights.stream()
                .map(this::mapToSearchResponse)
                .collect(Collectors.toList());
    }

    // Flight listesini SearchResponse listesine dönüştürmek
    public List<SearchResponse> mapToSearchResponseListOneWay(List<Flight> flights) {
        return flights.stream()
                .map(this::mapToSearchResponseOneWay)
                .collect(Collectors.toList());
    }
}
