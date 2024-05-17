package com.cetin.flightsearchapi.model;

import com.cetin.flightsearchapi.model.response.DepartureFlightResponse;
import com.cetin.flightsearchapi.model.response.ReturnFlightResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchResponse {

    private Long id;
    private DepartureFlightResponse departureFlight;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ReturnFlightResponse returnFlight;
    private BigDecimal price;
}
