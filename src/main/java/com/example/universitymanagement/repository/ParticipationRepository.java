package com.example.universitymanagement.repository;

import com.example.universitymanagement.entity.Participation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipationRepository extends JpaRepository<Participation,Integer> {
}
