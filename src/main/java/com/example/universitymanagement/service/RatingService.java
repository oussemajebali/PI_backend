package com.example.universitymanagement.service;

import com.example.universitymanagement.entity.Rating;
import com.example.universitymanagement.repository.RatingRepository;
import org.springframework.stereotype.Service;

@Service
public class RatingService {
    private final RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public Rating createRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    // Other necessary methods
}
