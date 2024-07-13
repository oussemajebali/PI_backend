package com.example.universitymanagement.repository;

import com.example.universitymanagement.entity.ClubMembership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubMermbershipRepository extends JpaRepository<ClubMembership, Integer> {
}
