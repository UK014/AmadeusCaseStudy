package com.example.demo.Flights;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightsRepository extends JpaRepository<Flights,Long> {
    @Modifying
    @Query("UPDATE Flights f SET f.departureAirport = :departureAirport, f.arrivalAirport = :arrivalAirport, " +
            "f.departureDatetime = :departureDatetime, f.returnDatetime = :returnDatetime, f.price = :price " +
            "WHERE f.id = :id")
    int updateFlight(@Param("id") Long id, @Param("departureAirport") String departureAirport,
                     @Param("arrivalAirport") String arrivalAirport, @Param("departureDatetime") LocalDateTime departureDatetime,
                     @Param("returnDatetime") LocalDateTime returnDatetime, @Param("price") double price);

    @Query("SELECT f FROM Flights f " +
            "WHERE f.departureAirport = :departureAirport " +
            "AND f.arrivalAirport = :arrivalAirport " +
            "AND YEAR(f.departureDatetime) = :departureYear " +
            "AND MONTH(f.departureDatetime) = :departureMonth " +
            "AND DAY(f.departureDatetime) = :departureDay " +
            "AND YEAR(f.returnDatetime) = :returnYear " +
            "AND MONTH(f.returnDatetime) = :returnMonth " +
            "AND DAY(f.returnDatetime) = :returnDay")
    List<Flights> findBySearchParams(@Param("departureAirport") String departureAirport,
                                     @Param("arrivalAirport") String arrivalAirport,
                                     @Param("departureYear") int departureYear,
                                     @Param("departureMonth") int departureMonth,
                                     @Param("departureDay") int departureDay,
                                     @Param("returnYear") int returnYear,
                                     @Param("returnMonth") int returnMonth,
                                     @Param("returnDay") int returnDay);

    @Query("SELECT f FROM Flights f " +
            "WHERE f.departureAirport = :departureAirport " +
            "AND f.arrivalAirport = :arrivalAirport " +
            "AND YEAR(f.departureDatetime) = :departureYear " +
            "AND MONTH(f.departureDatetime) = :departureMonth " +
            "AND DAY(f.departureDatetime) = :departureDay ")
    List<Flights> findBySearchParamsOneway(@Param("departureAirport") String departureAirport,
                                     @Param("arrivalAirport") String arrivalAirport,
                                     @Param("departureYear") int departureYear,
                                     @Param("departureMonth") int departureMonth,
                                     @Param("departureDay") int departureDay);


    @Query("SELECT f FROM Flights f " +
            "WHERE f.departureAirport = :departureAirport " +
            "AND f.arrivalAirport = :arrivalAirport " +
            "AND YEAR(f.departureDatetime) = :departureYear " +
            "AND MONTH(f.departureDatetime) = :departureMonth " +
            "AND DAY(f.departureDatetime) = :departureDay ")
    List<Flights> findBySearchParamsReturn(@Param("arrivalAirport") String arrivalAirport,
                                           @Param("departureAirport") String departureAirport,
                                           @Param("departureYear") int departureYear,
                                           @Param("departureMonth") int departureMonth,
                                           @Param("departureDay") int departureDay);
}
