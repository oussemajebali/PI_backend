package com.example.universitymanagement.repository;
import com.example.universitymanagement.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Integer> {

    List<Reservation> findBySpaceId(int spaceId);
    List<Reservation> findByUserId(int userId);
}
