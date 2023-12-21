package com.example.demo.Airports;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Airports {

    @Id
    private String id;
    private String city;

    public Airports(String id, String city) {
        this.id = id;
        this.city = city;
    }

    public Airports(String id) {
        this.id = id;
    }

    public Airports() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
