package com.example.universitymanagement.service;

import com.example.universitymanagement.entity.Club;
import com.example.universitymanagement.repository.ClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public  class ClubService {


    private final ClubRepository clubRepository;

    // Retrieve all clubs
    public List<Club> getAllClubs() {
        return clubRepository.findAll();
    }

    // Retrieve a club by its ID
    public Club getClubById(int id) {
        return clubRepository.findById(id).orElse(null);
    }

    // Create a new club
    public Club createClub(Club club) {
        return clubRepository.save(club);
    }

    // Update an existing club
    public Club updateClub(int id, Club club) {
        if (clubRepository.existsById(id)) {
            club.setId(id);
            return clubRepository.save(club);
        }
        return null; // or throw an exception
    }

    // Delete a club by its ID
    public void deleteClub(int id) {
        clubRepository.deleteById(id);
    }
}
