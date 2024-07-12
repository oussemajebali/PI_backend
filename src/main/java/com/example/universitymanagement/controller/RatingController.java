package com.example.universitymanagement.controller;

import com.example.universitymanagement.entity.Rating;
import com.example.universitymanagement.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rating")
@RequiredArgsConstructor
public class RatingController {
    private final RatingService ratingService;

    @PostMapping("/addrating")
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {
        Rating savedRating = ratingService.createRating(rating);
        return new ResponseEntity<>(savedRating, HttpStatus.CREATED);
    }

    // Other necessary methods
}
