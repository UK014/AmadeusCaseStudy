package com.example.demo.Schedule;

import com.example.demo.Flights.Flights;
import com.example.demo.Flights.FlightsService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Slf4j
@Service
public class ScheduledUpdate {

    @Autowired
    private final FlightsService flightsService;
    private final String postmanMockApiUrl = "https://c14aad65-ab7b-479e-8ca1-fea221301438.mock.pstmn.io/get";
    public ScheduledUpdate(FlightsService flightsService) {
        this.flightsService = flightsService;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void scheduledUpdate() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
                postmanMockApiUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<String>() {
                });

        if (response.getStatusCode().is2xxSuccessful()) {
            String responseBody = response.getBody();

            try {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(responseBody);

                JsonNode args = jsonNode.path("args");
                String departureAirport = args.path("departureAirport").asText();
                String arrivalAirport = args.path("arrivalAirport").asText();
                int price = args.path("price").asInt();
                LocalDateTime depdate = LocalDateTime.parse(args.path("departureDatetime").asText());
                LocalDateTime retdate = LocalDateTime.parse(args.path("returnDatetime").asText());
                Flights flights = new Flights(departureAirport,arrivalAirport,depdate,retdate,price);
                flightsService.createFlight(flights);
            } catch (Exception e) {
                log.error("Error parsing JSON response", e);
            }
        } else {
            log.error("Failed to retrieve data from {}", postmanMockApiUrl);
        }
    }



}





