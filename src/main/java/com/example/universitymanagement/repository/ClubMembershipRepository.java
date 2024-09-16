package com.example.universitymanagement.repository;

import com.example.universitymanagement.entity.ClubMembership;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubMembershipRepository extends JpaRepository<ClubMembership, Long> {
}
