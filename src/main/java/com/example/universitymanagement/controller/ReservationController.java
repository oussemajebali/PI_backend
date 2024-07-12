package com.example.universitymanagement.controller;

import com.example.universitymanagement.entity.Reservation;
import com.example.universitymanagement.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reservation")
@RequiredArgsConstructor
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/{id}")
    public Reservation getReservationById(@PathVariable int id) {
        return reservationService.getReservationById(id);
    }

    @GetMapping("/space/{spaceId}")
    public List<Reservation> getReservationsBySpace(@PathVariable int spaceId) {
        return reservationService.getReservationsBySpace(spaceId);
    }

    @GetMapping("/user/{user}")
    public List<Reservation> getReservationsByUser(@PathVariable String user) {
        return reservationService.getReservationsByUser(user);
    }

    @PostMapping
    public Reservation createReservation(@RequestBody Reservation reservation) {
        return reservationService.createReservation(reservation);
    }

    @PutMapping("/{id}")
    public Reservation updateReservation(@PathVariable int id, @RequestBody Reservation reservation) {
        return reservationService.updateReservation(id, reservation);
    }

    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable int id) {
        reservationService.deleteReservation(id);
    }
}
