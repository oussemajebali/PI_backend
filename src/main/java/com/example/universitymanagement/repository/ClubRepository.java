package com.example.universitymanagement.repository;

import com.example.universitymanagement.entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ClubRepository {
    @Repository
    public interface ClubRepository extends JpaRepository<Club, Long> {
        // You can add custom query methods here if needed
    }
}
