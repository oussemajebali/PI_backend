package com.example.universitymanagement.service;

import com.example.universitymanagement.entity.Event;
import com.example.universitymanagement.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Optional<Event> getEventById(int id) {
        return eventRepository.findById(id);
    }

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public Event updateEvent(int id, Event updatedEvent) {
        return eventRepository.findById(id)
                .map(event -> {
                    event.setTitle(updatedEvent.getTitle());
                    event.setDescription(updatedEvent.getDescription());
                    event.setStartTime(updatedEvent.getStartTime());
                    event.setEndTime(updatedEvent.getEndTime());
                    event.setLocation(updatedEvent.getLocation());
                    event.setMaxAttendees(updatedEvent.getMaxAttendees());
                    return eventRepository.save(event);
                })
                .orElseGet(() -> {
                    updatedEvent.setId(id);
                    return eventRepository.save(updatedEvent);
                });
    }

    public void deleteEvent(int id) {
        eventRepository.deleteById(id);
    }
}
