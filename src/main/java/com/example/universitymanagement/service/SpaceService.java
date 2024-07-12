package com.example.universitymanagement.service;

import com.example.universitymanagement.entity.Space;
import com.example.universitymanagement.repository.SpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpaceService {
    @Autowired
    private SpaceRepository spaceRepository;

    public List<Space> getAllSpaces() {
        return spaceRepository.findAll();
    }

    public Space getSpaceById(int id) {
        return spaceRepository.findById(id).orElse(null);
    }

    public List<Space> getAvailableSpaces() {
        return spaceRepository.findByAvailable(true);
    }
    public Space createSpace(Space space) {
        return spaceRepository.save(space);
    }

    public Space updateSpace(int id, Space spaceDetails) {
        Space space = spaceRepository.findById(id).orElse(null);
        if (space != null) {
            space.setName(spaceDetails.getName());
            space.setType(spaceDetails.getType());
            space.setAvailable(spaceDetails.isAvailable());
            space.setPhotoUrl(spaceDetails.getPhotoUrl());
            return spaceRepository.save(space);
        }
        return null;
    }

    public void deleteSpace(int id) {
        spaceRepository.deleteById(id);
    }
}
