package com.example.universitymanagement.controller;

import com.example.universitymanagement.entity.Rating;
import com.example.universitymanagement.entity.RatingRequest;
import com.example.universitymanagement.service.RatingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rating")
@RequiredArgsConstructor
public class RatingController {

    private static final Logger logger = LoggerFactory.getLogger(RatingController.class);

    private final RatingService ratingService;

    @PostMapping("/addrating")
    public ResponseEntity<Rating> createRating(@RequestBody String rawRequestBody) {
        logger.info("Raw Request Body: {}", rawRequestBody);
        ObjectMapper objectMapper = new ObjectMapper();
        RatingRequest ratingRequest;
        try {
            ratingRequest = objectMapper.readValue(rawRequestBody, RatingRequest.class);
        } catch (JsonProcessingException e) {
            logger.error("Failed to parse request body", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        logger.info("Parsed Rating Request: {}", ratingRequest);
        Rating savedRating = ratingService.createRating(ratingRequest);
        return new ResponseEntity<>(savedRating, HttpStatus.CREATED);
    }

}
