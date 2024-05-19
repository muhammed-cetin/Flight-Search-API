package com.cetin.flightsearchapi.handler.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    // Error Codes
    AIRPORT_NOT_FOUND( "Airport Not Found", HttpStatus.NOT_FOUND),
    FLIGHT_NOT_FOUND("Flight Not Found", HttpStatus.NOT_FOUND),
    INTERNAL_SERVER_ERROR("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String message;
    private final HttpStatus httpStatus;

    ErrorCode(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
