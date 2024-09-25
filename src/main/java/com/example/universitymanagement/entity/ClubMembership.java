package com.example.universitymanagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "club_memberships")
public class ClubMembership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String position;
    private String reason;

    @ManyToOne
    @JoinColumn(name = "club_id")
    @JsonBackReference
    private Club club;

    @Enumerated(EnumType.STRING)
    private MembershipStatus status;

    // Getters and setters
    @Enumerated(EnumType.STRING)
    private MembershipRole role; // e.g., FINANCIAL_OFFICER, EVENT_COORDINATOR, etc.

    // New field to hold the club ID for incoming requests
    @Transient // This annotation indicates that this field is not stored in the database
    private Long clubId;
}
