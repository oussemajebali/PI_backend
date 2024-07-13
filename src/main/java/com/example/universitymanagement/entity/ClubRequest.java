package com.example.universitymanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "club_request")
@Entity
public class ClubRequest {
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String clubName;

    private String clubDescription;

    @ManyToOne
    @JoinColumn(name = "club_manager_id")
    private User clubManager;

    private boolean approved;

}
