package com.example.universitymanagement.service;

import com.example.universitymanagement.entity.ClubRequest;
import com.example.universitymanagement.repository.ClubRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ClubRequestService {


    private final ClubRequestRepository clubRequestRepository;

    @Autowired
    public ClubRequestService(ClubRequestRepository clubRequestRepository) {
        this.clubRequestRepository = clubRequestRepository;
    }

    public List<ClubRequest> getAllClubRequests() {
        return clubRequestRepository.findAll();
    }

    public ClubRequest getClubRequestById(int id) {
        return clubRequestRepository.findById(id).orElse(null);
    }

    public ClubRequest createClubRequest(ClubRequest clubRequest) {
        return clubRequestRepository.save(clubRequest);
    }

    public ClubRequest updateClubRequest(int id, ClubRequest clubRequest) {
        // Check if the club request with the given ID exists
        if (clubRequestRepository.existsById(id)) {
            // Set the ID of the club request to be updated
            clubRequest.setId(id);
            // Save the updated club request to the database
            return clubRequestRepository.save(clubRequest);
        } else {
            return null; // or throw an exception indicating that the club request was not found
        }
    }

    public void deleteClubRequest(int id) {
        clubRequestRepository.deleteById(id);

    }
}
