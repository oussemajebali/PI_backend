package com.example.universitymanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonBackReference;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_participation")
public class Participation {
    @Id
    @GeneratedValue
    private int id;

    private String userName;
    private LocalDateTime participationTime;

    @JsonBackReference("event-participation")
    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

}
