package com.example.demo.Airports;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AirportsRepository extends JpaRepository<Airports,String> {
    @Modifying
    @Query("UPDATE Airports a SET a.id = :id, a.city =:city WHERE a.id = :id")
    int updateAirport(@Param("id") String id,@Param("city") String city);

}
