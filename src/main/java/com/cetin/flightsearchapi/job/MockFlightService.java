package com.cetin.flightsearchapi.job;

import com.cetin.flightsearchapi.model.request.FlightRequest;
import com.cetin.flightsearchapi.model.response.AirportResponse;
import com.cetin.flightsearchapi.service.AirportService;
import com.cetin.flightsearchapi.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MockFlightService {

    private final FlightService flightService;
    private final AirportService airportService;


    @Scheduled(cron = "0 0 2 * * ?") // Her gün saat 02:00'de çalışacak şekilde ayarlanmış bir Scheduled job
    public void updateFlightDataFromApi() {
        // Havaalanı bilgilerini veritabanından al
        List<AirportResponse> airports = airportService.getAllAirports();

        // Uçuş verilerini random olarak oluştur ve veritabanına kaydet
        Random random = new Random();
        for (int i = 0; i < 10; i++) { // Örnek olarak 10 adet uçuş oluşturuyoruz
            AirportResponse departureAirport = getRandomAirport(airports, random);
            AirportResponse arrivalAirport = getRandomAirport(airports, random);

            // Uçuş tarihini oluştur
            LocalDateTime departureDate = LocalDateTime.now().plusDays(random.nextInt(30)); // Şu andan itibaren 30 gün içinde bir tarih
            LocalDateTime returnDate = departureDate.plusDays(random.nextInt(10)); // Gidiş tarihinden itibaren 10 gün içinde dönüş tarihi

            // Uçuş fiyatını oluştur
            BigDecimal price = BigDecimal.valueOf(random.nextDouble() * 1000).setScale(2, BigDecimal.ROUND_HALF_UP); // Rastgele bir fiyat (0 - 1000 arası)

            // Uçuş bilgisini oluştur
            FlightRequest flightRequest = new FlightRequest();
            flightRequest.setDepartureAirport(departureAirport.getId());
            flightRequest.setArrivalAirport(arrivalAirport.getId());
            flightRequest.setDepartureDate(departureDate);
            flightRequest.setReturnDate(returnDate);
            flightRequest.setPrice(price);

            // Uçuş bilgisini veritabanına kaydet
            flightService.createFlight(flightRequest);
        }

        System.out.println("Scheduled job: Flight data updated successfully.");
    }

    // Veritabanından rastgele bir havaalanı bilgisini al
    private AirportResponse getRandomAirport(List<AirportResponse> airports, Random random) {
        return airports.get(random.nextInt(airports.size()));
    }
}
