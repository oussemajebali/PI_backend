package com.example.universitymanagement.controller;

import com.example.universitymanagement.entity.Club;
import com.example.universitymanagement.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/clubs")
@RequiredArgsConstructor
public class ClubController {

    private final ClubService clubService;

    // Create a new club
    @PostMapping
    public ResponseEntity<Club> createClub(@RequestBody Map<String, Object> payload) {
        String name = (String) payload.get("name");
        String description = (String) payload.get("description");
        int leaderId = Integer.valueOf(payload.get("leader").toString()); // Extract leaderId from the payload

        Club newClub = clubService.createClub(name, description, leaderId);
        return ResponseEntity.ok(newClub);
    }

    @PutMapping("/approve/{id}")
    @PreAuthorize("hasAuthority('UNIVERSITY_ADMIN')")
    public ResponseEntity<Club> approveClub(@PathVariable Long id) {
        return ResponseEntity.ok(clubService.approveClub(id));
    }

    @PutMapping("/reject/{id}")
    @PreAuthorize("hasAuthority('UNIVERSITY_ADMIN')")
    public ResponseEntity<Club> rejectClub(@PathVariable Long id) {
        return ResponseEntity.ok(clubService.rejectClub(id));
    }

    // Get all clubs
    @GetMapping
    public ResponseEntity<List<Club>> getAllClubs() {
        List<Club> clubs = clubService.getAllClubs();
        return ResponseEntity.ok(clubs);
    }

    // Get club by ID
    @GetMapping("/{id}")
    public ResponseEntity<Club> getClubById(@PathVariable Long id) {
        Club club = clubService.getClubById(id);
        return ResponseEntity.ok(club);
    }

    // Delete a club by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClub(@PathVariable Long id) {
        clubService.deleteClub(id);
        return ResponseEntity.noContent().build();
    }

    // Update a club
    @PutMapping("/{id}")
    public ResponseEntity<Club> updateClub(@PathVariable Long id, @RequestBody Map<String, Object> payload) {
        String name = (String) payload.get("name");
        String description = (String) payload.get("description");

        // Ensure 'leader' is extracted as an integer
        Object leaderObj = payload.get("leader");
        int leaderId = (leaderObj instanceof Number) ? ((Number) leaderObj).intValue() : Integer.valueOf(leaderObj.toString());

        Club updatedClub = clubService.updateClub(id, name, description, leaderId);
        return ResponseEntity.ok(updatedClub);
    }

}
