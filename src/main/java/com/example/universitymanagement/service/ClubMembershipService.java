package com.example.universitymanagement.service;

import com.example.universitymanagement.entity.ClubMembership;
import com.example.universitymanagement.repository.ClubMermbershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubMembershipService {

    private final ClubMermbershipRepository clubMembershipRepository;

    @Autowired
    public ClubMembershipService(ClubMermbershipRepository clubMembershipRepository) {
        this.clubMembershipRepository = clubMembershipRepository;
    }

    public List<ClubMembership> getAllClubMemberships() {
        return clubMembershipRepository.findAll();
    }

    public ClubMembership getClubMembershipById(int id) {
        return clubMembershipRepository.findById(id).orElse(null);
    }

    public ClubMembership createClubMembership(ClubMembership clubMembership) {
        return clubMembershipRepository.save(clubMembership);
    }

    public ClubMembership updateClubMembership(ClubMembership clubMembership) {
        return clubMembershipRepository.save(clubMembership);
    }

    public void deleteClubMembership(int id) {
        clubMembershipRepository.deleteById(id);
    }
}
