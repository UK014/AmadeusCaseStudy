package com.example.demo.Flights;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightsController {

    @Autowired
    private final FlightsService flightsService;

    public FlightsController(FlightsService flightsService) {
        this.flightsService = flightsService;
    }
    @PostMapping("/create")
    public Flights createFlight(@RequestBody String request){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            Flights flight = objectMapper.readValue(request, Flights.class);
            return flightsService.createFlight(flight);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/read")
    public List<Flights> read(){
        return flightsService.readFlights();
    }

    @PostMapping("/update")
    public Flights update(@RequestBody String request){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            Flights flight = objectMapper.readValue(request, Flights.class);
            return flightsService.updateFlights(flight);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/delete")
    public Flights delete(@RequestBody String request){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            Flights flight = objectMapper.readValue(request, Flights.class);
            return flightsService.deleteFlights(flight);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
