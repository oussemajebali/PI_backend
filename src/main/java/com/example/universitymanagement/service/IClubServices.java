package com.example.universitymanagement.service;

import com.example.universitymanagement.entity.Club;

import java.util.List;

public interface IClubServices {

    // Retrieve all clubs
    List<Club> getAllClubs();

    // Retrieve a club by its ID
    Club getClubById(Long id);

    // Create a new club
    Club createClub(Club club);

    // Update an existing club
    Club updateClub(Long id, Club club);

    // Delete a club by its ID
    void deleteClub(Long id);

}
