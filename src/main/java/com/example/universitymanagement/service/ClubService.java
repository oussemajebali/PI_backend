package com.example.universitymanagement.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.universitymanagement.entity.Club;
import com.example.universitymanagement.entity.ClubStatus;
import com.example.universitymanagement.entity.User;
import com.example.universitymanagement.repository.ClubRepository;
import com.example.universitymanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ClubService {

    private final ClubRepository clubRepository;
    private final UserRepository userRepository;

    private final Cloudinary cloudinary;

    public Club createClub(String name, String description, int leaderId, MultipartFile logo) {
        User leader = userRepository.findById(leaderId)
                .orElseThrow(() -> new IllegalArgumentException("Leader not found"));

        String logoUrl = uploadLogoToCloudinary(logo);

        Club club = new Club();
        club.setName(name);
        club.setDescription(description);
        club.setLeader(leader);
        club.setStatus(ClubStatus.PENDING);
        club.setLogoUrl(logoUrl);  // Set the uploaded logo URL

        return clubRepository.save(club);
    }

    private String uploadLogoToCloudinary(MultipartFile logo) {
        if (logo.isEmpty()) {
            return null; // Return null if no file is uploaded
        }
        try {
            Map uploadResult = cloudinary.uploader().upload(logo.getBytes(), ObjectUtils.emptyMap());
            return uploadResult.get("url").toString();
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload logo to Cloudinary", e);
        }
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
