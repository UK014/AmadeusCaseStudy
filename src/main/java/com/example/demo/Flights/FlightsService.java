package com.example.demo.Flights;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightsService {

    @Autowired
    private final FlightsRepository flightsRepository;

    public FlightsService(FlightsRepository flightsRepository) {
        this.flightsRepository = flightsRepository;
    }
    public Flights createFlight(Flights flights) {
        return flightsRepository.save(flights);
    }

    public List<Flights> readFlights(){
        return flightsRepository.findAll();
    }

    @Transactional
    public Flights updateFlights(Flights flights){
        flightsRepository.updateFlight(flights.getId(),
                flights.getDepartureAirport(),
                flights.getArrivalAirport(),
                flights.getDepartureDatetime(),
                flights.getReturnDatetime(),
                flights.getPrice());
        return flights;
    }

    public Flights deleteFlights(Flights flights){
        flightsRepository.deleteById(flights.getId());
        return flights;
    }

    public List<Flights> findFlightsBySearchParams(String departureAirport, String arrivalAirport,
                                                   int departureYear, int departureMonth, int departureDay,
                                                   int returnYear, int returnMonth, int returnDay) {
        return flightsRepository.findBySearchParams(departureAirport, arrivalAirport,
                departureYear, departureMonth, departureDay,
                returnYear, returnMonth, returnDay);
    }

    public List<Flights> findFlightsBySearchParamsOneway(String departureAirport, String arrivalAirport,
                                                   int departureYear, int departureMonth, int departureDay) {
        return flightsRepository.findBySearchParamsOneway(departureAirport, arrivalAirport,
                departureYear, departureMonth, departureDay);
    }

    public List<Flights> findFlightsBySearchParamsReturn(String departureAirport, String arrivalAirport,
                                                         int departureYear, int departureMonth, int departureDay) {
        return flightsRepository.findBySearchParamsReturn(arrivalAirport, departureAirport,
                departureYear, departureMonth, departureDay);
    }

}
