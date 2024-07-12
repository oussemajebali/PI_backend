package com.example.universitymanagement.service;

import com.example.universitymanagement.entity.Event;
import com.example.universitymanagement.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final CloudinaryService cloudinaryService;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Optional<Event> getEventById(int id) {
        return eventRepository.findById(id);
    }

    public Event createEvent(Event event, List<MultipartFile> images) {
        List<String> imageUrls = cloudinaryService.uploadImages(images);
        event.setImages(String.join(";", imageUrls));
        return eventRepository.save(event);
    }

    public Event updateEvent(int id, Event event) {
        return eventRepository.save(event);
    }

    public void deleteEvent(int id) {
        eventRepository.deleteById(id);
    }
}
