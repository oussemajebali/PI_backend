package com.example.universitymanagement.service;

import com.example.universitymanagement.entity.Participation;
import com.example.universitymanagement.repository.ParticipationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipationService {
    @Autowired
    private ParticipationRepository participationRepository;

    public List<Participation> getAllParticipations() {
        return participationRepository.findAll();
    }

    public Participation getParticipationById(int id) {
        return participationRepository.findById(id).orElse(null);
    }

    public Participation createParticipation(Participation participation) {
        return participationRepository.save(participation);
    }

    public Participation updateParticipation(int id, Participation participationDetails) {
        Participation participation = participationRepository.findById(id).orElse(null);
        if (participation != null) {
            participation.setUserName(participationDetails.getUserName());
            participation.setParticipationTime(participationDetails.getParticipationTime());
            participation.setEvent(participationDetails.getEvent());
            return participationRepository.save(participation);
        }
        return null;
    }

    public void deleteParticipation(int id) {
        participationRepository.deleteById(id);
    }
}
