package com.example.universitymanagement.service;

import com.example.universitymanagement.entity.Club;
import com.example.universitymanagement.entity.ClubStatus;
import com.example.universitymanagement.entity.User;
import com.example.universitymanagement.repository.ClubRepository;
import com.example.universitymanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClubService {

    private final ClubRepository clubRepository;
    private final UserRepository userRepository;

    // Create a new club
    public Club createClub(String name, String description, int leaderId) {
        User leader = userRepository.findById(leaderId)
                .orElseThrow(() -> new IllegalArgumentException("Leader not found"));

        Club club = new Club();
        club.setName(name);
        club.setDescription(description);
        club.setLeader(leader);
        club.setStatus(ClubStatus.PENDING);

        return clubRepository.save(club);
    }

    // Approve a club
    public Club approveClub(Long clubId) {
        Club club = clubRepository.findById(clubId)
                .orElseThrow(() -> new IllegalArgumentException("Club not found"));
        club.setStatus(ClubStatus.APPROVED);
        return clubRepository.save(club);
    }

    // Reject a club
    public Club rejectClub(Long clubId) {
        Club club = clubRepository.findById(clubId)
                .orElseThrow(() -> new IllegalArgumentException("Club not found"));
        club.setStatus(ClubStatus.REJECTED);
        return clubRepository.save(club);
    }

    // Get all clubs
    public List<Club> getAllClubs() {
        return clubRepository.findAll();
    }

    // Get club by ID
    public Club getClubById(Long id) {
        return clubRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Club not found"));
    }

    // Delete a club by ID
    public void deleteClub(Long id) {
        Club club = clubRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Club not found"));
        clubRepository.delete(club);
    }

    // Update a club
    public Club updateClub(Long id, String name, String description, int leaderId) {
        Club club = clubRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Club not found"));

        User leader = userRepository.findById(leaderId)
                .orElseThrow(() -> new IllegalArgumentException("Leader not found"));

        club.setName(name);
        club.setDescription(description);
        club.setLeader(leader);

        return clubRepository.save(club);
    }
}
