package com.example.universitymanagement.controller;

import com.example.universitymanagement.entity.ClubMembership;
import com.example.universitymanagement.service.ClubMembershipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/memberships")
@RequiredArgsConstructor
public class ClubMembershipController {

    private final ClubMembershipService clubMembershipService;

    @PostMapping("/request")
    public ResponseEntity<ClubMembership> requestToJoin(@RequestBody ClubMembership membership) {
        return ResponseEntity.ok(clubMembershipService.saveJoinRequest(membership));
    }

    @PutMapping("/approve/{id}")
    public ResponseEntity<ClubMembership> approveRequest(@PathVariable Long id) {
        return ResponseEntity.ok(clubMembershipService.approveJoinRequest(id));
    }

    @PutMapping("/reject/{id}")
    public ResponseEntity<ClubMembership> rejectRequest(@PathVariable Long id) {
        return ResponseEntity.ok(clubMembershipService.rejectJoinRequest(id));
    }
}
