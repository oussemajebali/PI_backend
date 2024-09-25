package com.example.universitymanagement.service;

import com.example.universitymanagement.entity.Club;
import com.example.universitymanagement.entity.ClubMembership;
import com.example.universitymanagement.entity.MembershipStatus;
import com.example.universitymanagement.repository.ClubMembershipRepository;
import com.example.universitymanagement.repository.ClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClubMembershipService {

    private final ClubMembershipRepository clubMembershipRepository;
    private final ClubRepository clubRepository;

    public ClubMembership saveJoinRequest(ClubMembership membership) {
        membership.setStatus(MembershipStatus.PENDING); // Default status is PENDING
        return clubMembershipRepository.save(membership);
    }
    public ClubMembership processJoinRequest(ClubMembership membership) {
        // Check if the clubId is present in the membership
        if (membership.getClubId() == null) {
            throw new RuntimeException("Club ID is missing");
        }

        // Fetch the associated club by its ID using clubId
        Club club = clubRepository.findById(membership.getClubId())
                .orElseThrow(() -> new RuntimeException("Club not found"));

        // Set the retrieved club to the membership object
        membership.setClub(club);

        // Save the membership request (either new or update)
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
