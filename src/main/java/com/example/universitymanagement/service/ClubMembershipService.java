package com.example.universitymanagement.service;

import com.example.universitymanagement.entity.ClubMembership;
import com.example.universitymanagement.entity.MembershipStatus;
import com.example.universitymanagement.repository.ClubMembershipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClubMembershipService {

    private final ClubMembershipRepository clubMembershipRepository;

    public ClubMembership saveJoinRequest(ClubMembership membership) {
        membership.setStatus(MembershipStatus.PENDING); // Default status is PENDING
        return clubMembershipRepository.save(membership);
    }

    public ClubMembership approveJoinRequest(Long id) {
        ClubMembership membership = clubMembershipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));
        membership.setStatus(MembershipStatus.APPROVED);
        return clubMembershipRepository.save(membership);
    }

    public ClubMembership rejectJoinRequest(Long id) {
        ClubMembership membership = clubMembershipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Request not found"));
        membership.setStatus(MembershipStatus.REJECTED);
        return clubMembershipRepository.save(membership);
    }
}
