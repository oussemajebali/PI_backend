package com.example.universitymanagement.repository;

import com.example.universitymanagement.entity.ClubRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubRequestRepository extends JpaRepository<ClubRequest, Integer> {
}
