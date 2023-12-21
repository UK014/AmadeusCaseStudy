package com.example.demo.Flights;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Flights {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String departureAirport;
    private String arrivalAirport;
    private LocalDateTime departureDatetime;
    private LocalDateTime returnDatetime;

    private double price;

    public Flights(Long id, String departureAirport, String arrivalAirport, LocalDateTime departureDatetime, LocalDateTime returnDatetime, double price) {
        this.id = id;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureDatetime = departureDatetime;
        this.returnDatetime = returnDatetime;
        this.price = price;
    }

    public Flights(String departureAirport, String arrivalAirport, LocalDateTime departureDatetime, LocalDateTime returnDatetime, double price) {
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureDatetime = departureDatetime;
        this.returnDatetime = returnDatetime;
        this.price = price;
    }

    public Flights(Long id) {
        this.id = id;
    }

    public Flights() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departure_airport) {
        this.departureAirport = departure_airport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrival_airport) {
        this.arrivalAirport = arrival_airport;
    }

    public LocalDateTime getDepartureDatetime() {
        return departureDatetime;
    }

    public void setDepartureDatetime(LocalDateTime departure_datetime) {
        this.departureDatetime = departure_datetime;
    }

    public LocalDateTime getReturnDatetime() {
        return returnDatetime;
    }

    public void setReturnDatetime(LocalDateTime return_datetime) {
        this.returnDatetime = return_datetime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
