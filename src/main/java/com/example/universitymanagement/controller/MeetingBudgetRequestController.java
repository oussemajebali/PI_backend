package com.example.universitymanagement.controller;

import com.example.universitymanagement.entity.MeetingBudgetRequest;
import com.example.universitymanagement.service.MeetingBudgetRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/requests")
@RequiredArgsConstructor
public class MeetingBudgetRequestController {

    private final MeetingBudgetRequestService requestService;

    @PostMapping
    public ResponseEntity<MeetingBudgetRequest> createRequest(@RequestBody MeetingBudgetRequest request) {
        return ResponseEntity.ok(requestService.createRequest(request));
    }

    @PutMapping("/approve/{id}")
    public ResponseEntity<MeetingBudgetRequest> approveRequest(@PathVariable Long id) {
        return ResponseEntity.ok(requestService.approveRequest(id));
    }

    @PutMapping("/reject/{id}")
    public ResponseEntity<MeetingBudgetRequest> rejectRequest(@PathVariable Long id) {
        return ResponseEntity.ok(requestService.rejectRequest(id));
    }

    // Other endpoints as needed
}
