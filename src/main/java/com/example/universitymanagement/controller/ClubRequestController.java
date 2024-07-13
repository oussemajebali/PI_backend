package com.example.universitymanagement.controller;

import com.example.universitymanagement.entity.ClubRequest;
import com.example.universitymanagement.service.ClubRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/clubrequests")
public class ClubRequestController {


    private final ClubRequestService clubRequestService;

    @Autowired
    public ClubRequestController(ClubRequestService clubRequestService) {
        this.clubRequestService = clubRequestService;
    }

    // Retrieve all club requests
    @GetMapping
    public ResponseEntity<List<ClubRequest>> getAllClubRequests() {
        List<ClubRequest> clubRequests = clubRequestService.getAllClubRequests();
        return new ResponseEntity<>(clubRequests, HttpStatus.OK);
    }

    // Retrieve a club request by ID
    @GetMapping("/{id}")
    public ResponseEntity<ClubRequest> getClubRequestById(@PathVariable int id) {
        ClubRequest clubRequest = clubRequestService.getClubRequestById(id);
        if (clubRequest != null) {
            return new ResponseEntity<>(clubRequest, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Create a new club request
    @PostMapping("/addclubrequest")
    public ResponseEntity<ClubRequest> createClubRequest(@RequestBody ClubRequest clubRequest) {
        ClubRequest createdClubRequest = clubRequestService.createClubRequest(clubRequest);
        return new ResponseEntity<>(createdClubRequest, HttpStatus.CREATED);
    }

    // Update an existing club request
    @PutMapping("/{id}")
    public ResponseEntity<ClubRequest> updateClubRequest(@PathVariable int id, @RequestBody ClubRequest clubRequest) {
        ClubRequest updatedClubRequest = clubRequestService.updateClubRequest(id, clubRequest);
        if (updatedClubRequest != null) {
            return new ResponseEntity<>(updatedClubRequest, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}


