package com.example.universitymanagement.controller;

import com.example.universitymanagement.entity.Club;
import com.example.universitymanagement.service.ClubService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clubs")
public class ClubController {

    private final ClubService clubService;


    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    //  retrieve all clubs
    @GetMapping
    public ResponseEntity<List<Club>> getAllClubs() {
        List<Club> clubs = clubService.getAllClubs();
        return new ResponseEntity<>(clubs, HttpStatus.OK);
    }

    //  retrieve a club by ID
    @GetMapping("/{id}")
    public ResponseEntity<Club> getClubById(@PathVariable int id) {
        Club club = clubService.getClubById(id);
        if (club != null) {
            return new ResponseEntity<>(club, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //  create a new club
    @PostMapping ("/addclub")
    public ResponseEntity<Club> createClub(@RequestBody Club club) {
        Club createdClub = clubService.createClub(club);
        return new ResponseEntity<>(createdClub, HttpStatus.CREATED);
    }
    //  update an existing club
    @PutMapping("/{id}")
    public ResponseEntity<Club> updateClub(@PathVariable int id, @RequestBody Club club) {
        Club updatedClub = clubService.updateClub(id, club);
        if (updatedClub != null) {
            return new ResponseEntity<>(updatedClub, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //  delete a club by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClub(@PathVariable int id) {
        clubService.deleteClub(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
