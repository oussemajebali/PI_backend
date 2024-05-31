package com.example.universitymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.universitymanagement.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
