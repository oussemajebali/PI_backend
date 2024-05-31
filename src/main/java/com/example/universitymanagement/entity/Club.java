package com.example.universitymanagement.entity;


import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

@Entity
    public class Club {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;
        private String description;


@JoinColumn(name = "CLUB_LEADER_id", nullable = false) // each club must have a leader
private User CLUB_LEADER;


@ManyToMany(mappedBy = "clubs")
private List<User>CLUB_MEMBER ;


public Club(String name, String description, User CLUB_LEADER) {
    this.name = name;
    this.description = description;
    this.CLUB_LEADER = CLUB_LEADER;
}
}



// Getters and Setters
// Assuming you have getters and setters for all fields
