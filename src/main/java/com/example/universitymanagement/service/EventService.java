package com.example.universitymanagement.service;

import com.example.universitymanagement.entity.Event;
import com.example.universitymanagement.entity.Participation;
import com.example.universitymanagement.repository.EventRepository;
import com.example.universitymanagement.repository.ParticipationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final ParticipationRepository participationRepository;

    public EventService(EventRepository eventRepository, ParticipationRepository participationRepository) {
        this.eventRepository = eventRepository;
        this.participationRepository = participationRepository;
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Optional<Event> getEventById(int id) {
        return eventRepository.findById(id);
    }

    public Event createEvent(Event event, List<MultipartFile> images) {
        // Handle image upload logic here if needed
        return eventRepository.save(event);
    }

    @Transactional
    public Event updateEvent(int id, Event event) {
        return eventRepository.findById(id)
                .map(existingEvent -> {
                    existingEvent.setTitle(event.getTitle());
                    existingEvent.setDescription(event.getDescription());
                    existingEvent.setStartTime(event.getStartTime());
                    existingEvent.setEndTime(event.getEndTime());
                    existingEvent.setLocation(event.getLocation());
                    existingEvent.setMaxAttendees(event.getMaxAttendees());
                    existingEvent.setImages(event.getImages());
                    return eventRepository.save(existingEvent);
                })
                .orElseThrow(() -> new RuntimeException("Event not found"));
    }

    public void deleteEvent(int id) {
        eventRepository.deleteById(id);
    }

    @Transactional
    public Participation createParticipation(Participation participation) {
        Participation savedParticipation = participationRepository.save(participation);
        Event event = savedParticipation.getEvent();

        if (event != null) {
            event.getParticipations().add(savedParticipation);
            eventRepository.save(event);
        }
        return savedParticipation;
    }
}
