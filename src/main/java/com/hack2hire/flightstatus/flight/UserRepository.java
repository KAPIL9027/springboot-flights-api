package com.hack2hire.flightstatus.flight;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByFlightNumber(String flightNumber);
}
