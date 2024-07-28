package com.hack2hire.flightstatus.flight;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
//@RequestMapping("api/v1/flights")

public class FlightController {

    private final FlightService flightService;
    private final UserService userService;
    private final EmailSenderService emailService;


    @Autowired
    public FlightController(FlightService flightService, UserService userService, EmailSenderService emailService) {
        this.flightService = flightService;
        this.userService = userService;
        this.emailService = emailService;
    }
    @GetMapping("/")
    public List<Flight> getAllFlights(){
        return this.flightService.getFlights();
    }

    @PostMapping("/")
    public ResponseEntity<Object> createFlight(@RequestBody Flight flight){
        return flightService.newFlight(flight);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateFlightById(@PathVariable Integer id, @RequestBody Flight updatedFlight){
        Flight flight = (Flight)flightService.getFlightById(id).getBody();
        if(!flight.getFlight_status().equals(updatedFlight.getFlight_status()) || !flight.getGate().equals(updatedFlight.getGate())){
            List<User> subscribedUsers = userService.getUsersByFlightNumber(flight.getFlightNumber());
            for (User user : subscribedUsers) {
                StringBuilder statusMessage = new StringBuilder();
                if(!flight.getFlight_status().equals(updatedFlight.getFlight_status()) && !flight.getGate().equals(updatedFlight.getGate()))
                statusMessage.append("Your flight(" + flight.getFlightNumber()+") has been changed to "+updatedFlight.getFlight_status()+" and gate has changed to "+updatedFlight.getGate());
                else if(!flight.getFlight_status().equals(updatedFlight.getFlight_status()))
                    statusMessage.append("Your flight(" + flight.getFlightNumber()+") has been changed to "+updatedFlight.getFlight_status());
                else if(!flight.getFlight_status().equals(updatedFlight.getGate()))
                    statusMessage.append("Your flight(" + flight.getFlightNumber()+") gate has been changed to "+updatedFlight.getGate());
                emailService.sendEmail(user.getEmail(),
                        "Flight Status Update",
                        statusMessage.toString());
            }
        }
        return this.flightService.updateFlight(id,updatedFlight);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteFlightById(@PathVariable Integer id){
        return this.flightService.deleteFlight(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getFlightById(@PathVariable Integer id){
        return this.flightService.getFlightById(id);
    }


    @PostMapping("/subscribe")
    public ResponseEntity<Object> subscribe(@RequestBody User user){
        Flight flight  = flightService.getFlightByNumber(user.getFlightNumber());
        if(flight != null){
            emailService.sendEmail(user.getEmail(),"Subscription Confirmation","You have successfully subscribed to notifications for flight " + user.getFlightNumber());
            return userService.newUser(user);
        }

        else
            return ResponseEntity.notFound().build();
    }


}
