package com.example.universitymanagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "club")
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "leader_id", nullable = false)
    private User leader;

    @Enumerated(EnumType.STRING)
    private ClubStatus status; // e.g., PENDING, APPROVED, REJECTED

    @OneToMany(mappedBy = "club")
    @JsonIgnore // Ignore this field during serialization
    private List<ClubMembership> memberships;
}
