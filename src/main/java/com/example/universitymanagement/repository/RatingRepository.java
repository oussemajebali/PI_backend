package com.example.universitymanagement.repository;

import com.example.universitymanagement.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Integer> {
}
