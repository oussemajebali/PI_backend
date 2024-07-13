package com.example.universitymanagement.controller;

import com.example.universitymanagement.entity.ClubMembership;
import com.example.universitymanagement.service.ClubMembershipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clubmemberships")
public class ClubMembershipController {


    private final ClubMembershipService clubMembershipService;

    @Autowired
    public ClubMembershipController(ClubMembershipService clubMembershipService) {
        this.clubMembershipService = clubMembershipService;
    }

    @GetMapping
    public ResponseEntity<List<ClubMembership>> getAllClubMemberships() {
        List<ClubMembership> memberships = clubMembershipService.getAllClubMemberships();
        return new ResponseEntity<>(memberships, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClubMembership> getClubMembershipById(@PathVariable int id) {
        ClubMembership membership = clubMembershipService.getClubMembershipById(id);
        if (membership != null) {
            return new ResponseEntity<>(membership, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping ("/addclubmembership")
    public ResponseEntity<ClubMembership> createClubMembership(@RequestBody ClubMembership clubMembership) {
        ClubMembership createdMembership = clubMembershipService.createClubMembership(clubMembership);
        return new ResponseEntity<>(createdMembership, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClubMembership> updateClubMembership(@PathVariable int id, @RequestBody ClubMembership clubMembership) {
        if (clubMembershipService.getClubMembershipById(id) != null) {
            clubMembership.setId(id);
            ClubMembership updatedMembership = clubMembershipService.updateClubMembership(clubMembership);
            return new ResponseEntity<>(updatedMembership, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClubMembership(@PathVariable int id) {
        if (clubMembershipService.getClubMembershipById(id) != null) {
            clubMembershipService.deleteClubMembership(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
