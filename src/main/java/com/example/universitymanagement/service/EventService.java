package com.example.universitymanagement.service;

import com.example.universitymanagement.entity.Event;
import com.example.universitymanagement.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event getEventById(int id) {
        return eventRepository.findById(id).orElse(null);
    }

    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    public Event updateEvent(int id, Event eventDetails) {
        Event event = eventRepository.findById(id).orElse(null);
        if (event != null) {
            event.setName(eventDetails.getName());
            event.setDescription(eventDetails.getDescription());
            event.setStartTime(eventDetails.getStartTime());
            event.setEndTime(eventDetails.getEndTime());
            return eventRepository.save(event);
        }
        return null;
    }

    public void deleteEvent(int id) {
        eventRepository.deleteById(id);
    }
}
