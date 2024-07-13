package com.example.universitymanagement.service;

import com.example.universitymanagement.entity.RatingRequest;
import com.example.universitymanagement.entity.Event;
import com.example.universitymanagement.entity.Rating;
import com.example.universitymanagement.entity.User;
import com.example.universitymanagement.repository.EventRepository;
import com.example.universitymanagement.repository.RatingRepository;
import com.example.universitymanagement.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RatingService {

    private static final Logger logger = LoggerFactory.getLogger(RatingService.class);

    private final RatingRepository ratingRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public RatingService(RatingRepository ratingRepository, EventRepository eventRepository, UserRepository userRepository) {
        this.ratingRepository = ratingRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public Rating createRating(RatingRequest ratingRequest) {
        logger.info("Creating rating with request: {}", ratingRequest);

        // Validate event
        Optional<Event> eventOpt = eventRepository.findById(ratingRequest.getEventId());
        if (eventOpt.isEmpty()) {
            logger.error("Event not found with id: {}", ratingRequest.getEventId());
            throw new IllegalArgumentException("Event not found");
        }

        // Validate user
        Optional<User> userOpt = userRepository.findById(ratingRequest.getUserId());
        if (userOpt.isEmpty()) {
            logger.error("User not found with id: {}", ratingRequest.getUserId());
            throw new IllegalArgumentException("User not found");
        }

        // Create Rating entity
        Rating rating = new Rating();
        rating.setEvent(eventOpt.get());
        rating.setUser(userOpt.get());
        rating.setStars(ratingRequest.getStars());

        Rating savedRating = ratingRepository.save(rating);
        logger.info("Rating saved: {}", savedRating);
        return savedRating;
    }
}
