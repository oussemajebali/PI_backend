package com.example.universitymanagement.service;

import com.example.universitymanagement.entity.Event;
import com.example.universitymanagement.entity.Participation;
import com.example.universitymanagement.repository.EventRepository;
import com.example.universitymanagement.repository.ParticipationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParticipationService {
    @Autowired
    private ParticipationRepository participationRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private EventService eventService; // Inject EventService
    public List<Participation> getAllParticipations() {
        return participationRepository.findAll();
    }

    public Participation getParticipationById(int id) {
        return participationRepository.findById(id).orElse(null);
    }

    @Transactional
    public Participation createParticipation(Participation participation) {
        // Ensure event is managed and updated
        Event event = participation.getEvent();
        event.getParticipations().add(participation); // Add participation to event
        event.setMaxAttendees(event.getMaxAttendees() - 1); // Decrease maxAttendees

        eventService.updateEvent(event.getId(), event); // Update event in database

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
