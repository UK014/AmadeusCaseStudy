package com.example.demo.Airports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AirportsService {
    @Autowired
    private final AirportsRepository airportsRepository;

    public AirportsService(AirportsRepository airportsRepository) {
        this.airportsRepository = airportsRepository;
    }

    public Airports createAirports(Airports airports) {
        return airportsRepository.save(airports);
    }

    public List<Airports> readAirports(){
        return airportsRepository.findAll();
    }
    @Transactional
    public Airports updateAirports(Airports airports){
        airportsRepository.updateAirport(airports.getId(),
                airports.getCity());

        return airports;
    }

    public Airports deleteAirports(Airports airports){
        airportsRepository.deleteById(airports.getId());
        return airports;
    }
}
