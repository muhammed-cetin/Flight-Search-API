package com.cetin.flightsearchapi.model;

import com.cetin.flightsearchapi.model.response.DepartureResponse;
import com.cetin.flightsearchapi.model.response.ReturnResponse;
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
    private DepartureResponse round;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ReturnResponse trip;
    private BigDecimal price;
}
