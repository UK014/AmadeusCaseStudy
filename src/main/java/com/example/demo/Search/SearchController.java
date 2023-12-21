package com.example.demo.Search;

import com.example.demo.Flights.Flights;
import com.example.demo.Flights.FlightsService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class SearchController {
    @Autowired
    private final FlightsService flightsService;

    public SearchController(FlightsService flightsService) {
        this.flightsService = flightsService;
    }

    @PostMapping("/search")
    public List<Flights> search(@RequestBody String request){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            Flights flight = objectMapper.readValue(request, Flights.class);
            JsonNode rootNode = objectMapper.readTree(request);
            String returnDate = rootNode.path("returnDatetime").asText();
            if(Objects.equals(returnDate, "")){
                return flightsService.findFlightsBySearchParamsOneway(flight.getDepartureAirport(),
                        flight.getArrivalAirport(),
                        flight.getDepartureDatetime().getYear(),
                        flight.getDepartureDatetime().getMonthValue(),
                        flight.getDepartureDatetime().getDayOfMonth());
            } else{
                List<Flights> roundTrip = new ArrayList<>();
                roundTrip.addAll(flightsService.findFlightsBySearchParams(flight.getDepartureAirport(),
                        flight.getArrivalAirport(),
                        flight.getDepartureDatetime().getYear(),
                        flight.getDepartureDatetime().getMonthValue(),
                        flight.getDepartureDatetime().getDayOfMonth(),
                        flight.getReturnDatetime().getYear(),
                        flight.getReturnDatetime().getMonthValue(),
                        flight.getReturnDatetime().getDayOfMonth()));

                roundTrip.addAll(flightsService.findFlightsBySearchParamsReturn(
                        flight.getArrivalAirport(),
                        flight.getDepartureAirport(),
                        flight.getReturnDatetime().getYear(),
                        flight.getReturnDatetime().getMonthValue(),
                        flight.getReturnDatetime().getDayOfMonth()));

                return roundTrip;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
