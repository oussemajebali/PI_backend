package com.example.universitymanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table (name = "club_membership")

public class ClubMembership{
@Setter
@Getter
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;

@ManyToOne
@JoinColumn(name = "user_id")
private User user;

@ManyToOne
@JoinColumn(name = "club_id")
private Club club;

private boolean isActive;

// Additional fields and methods as needed
}
