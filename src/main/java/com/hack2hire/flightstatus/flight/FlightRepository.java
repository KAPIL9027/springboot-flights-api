package com.hack2hire.flightstatus.flight;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, Integer> {
      Flight findByFlightNumber(String flightNumber);
}
