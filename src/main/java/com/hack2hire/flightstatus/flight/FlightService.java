package com.hack2hire.flightstatus.flight;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    private final FlightRepository flightRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public ResponseEntity<Object> newFlight(Flight flight){
        flightRepository.save(flight);
        return new ResponseEntity<>(flight, HttpStatus.CREATED);
    }

    public List<Flight> getFlights(){
        return this.flightRepository.findAll();
    }

    public ResponseEntity<Object> updateFlight(Integer id, Flight updatedFlight){
        Optional<Flight> flightOptional = flightRepository.findById(id);

        if(!flightOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }

        Flight existingFlight = flightOptional.get();
        if(updatedFlight.getFlightNumber() != null)
        existingFlight.setFlightNumber(updatedFlight.getFlightNumber());
        if(updatedFlight.getFlight_status() != null)
        existingFlight.setFlight_status(updatedFlight.getFlight_status());
        if(updatedFlight.getDeparture_time() != null)
        existingFlight.setDeparture_time(updatedFlight.getDeparture_time());
        if(updatedFlight.getArrival_time() != null)
        existingFlight.setArrival_time(updatedFlight.getArrival_time());
        if(updatedFlight.getGate() != null)
        existingFlight.setGate(updatedFlight.getGate());
        flightRepository.save(existingFlight);
        return ResponseEntity.ok(existingFlight);
    }

    public ResponseEntity<Object> deleteFlight(Integer id){
        Optional<Flight> flightOptional = flightRepository.findById(id);
        if(!flightOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        flightRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Object> getFlightById(Integer id){
        Optional<Flight> flightOptional = flightRepository.findById(id);
        if(!flightOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Flight flight = flightOptional.get();
        return ResponseEntity.ok(flight);
    }

    public Flight getFlightByNumber(String flightNumber){
        Flight flight = flightRepository.findByFlightNumber(flightNumber);
        if(flight != null)
        return flight;
        else
            return null;
    }
}
