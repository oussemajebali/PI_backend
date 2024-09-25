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

    @PostMapping("/process")
    public ResponseEntity<ClubMembership> processJoinRequest(@RequestBody ClubMembership membership) {
        return ResponseEntity.ok(clubMembershipService.processJoinRequest(membership));
    }
}
