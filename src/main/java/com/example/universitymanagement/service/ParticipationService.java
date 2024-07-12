package com.example.universitymanagement.service;

import com.example.universitymanagement.entity.Event;
import com.example.universitymanagement.entity.Participation;
import com.example.universitymanagement.repository.EventRepository;
import com.example.universitymanagement.repository.ParticipationRepository;
import jakarta.persistence.EntityNotFoundException;
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
    private EventService eventService;

    public List<Participation> getAllParticipations() {
        return participationRepository.findAll();
    }

    public Participation getParticipationById(int id) {
        return participationRepository.findById(id).orElse(null);
    }

    @Transactional
    public Participation createParticipation(Participation participation) {
        // Fetch event by ID and update maxAttendees
        Event event = eventRepository.findById(participation.getEvent().getId()).orElse(null);
        if (event != null) {
            event.setMaxAttendees(event.getMaxAttendees() - 1); // Decrease maxAttendees
            eventRepository.save(event); // Save updated event
        } else {
            throw new EntityNotFoundException("Event not found with ID: " + participation.getEvent().getId());
        }

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

