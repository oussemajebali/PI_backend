package com.example.universitymanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_space")
public class Space {
    @Id
    @GeneratedValue
    private int id;

    private String name;
    @Enumerated(EnumType.STRING)
    private SpaceType type;

    private boolean available;

    private String photoUrl;

    @OneToMany(mappedBy = "space", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations;
}
