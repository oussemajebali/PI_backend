package com.example.universitymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.universitymanagement.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
