package com.example.demo.Airports;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airports")
public class AirportsController {

    @Autowired
    private final AirportsService airportsService;

    public AirportsController(AirportsService airportsService) {
        this.airportsService = airportsService;
    }
    @PostMapping("/create")
    public Airports CreateAirport(@RequestBody String request){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            Airports airports = objectMapper.readValue(request, Airports.class);
            return airportsService.createAirports(airports);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/read")
    public List<Airports> Read(){
        return airportsService.readAirports();
    }

    @PostMapping("/update")
    public Airports Update(@RequestBody String request){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            Airports airports = objectMapper.readValue(request, Airports.class);
            return airportsService.updateAirports(airports);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("delete")
    public Airports Delete(@RequestBody String request){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        try {
            Airports airports = objectMapper.readValue(request, Airports.class);
            return airportsService.deleteAirports(airports);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



}
