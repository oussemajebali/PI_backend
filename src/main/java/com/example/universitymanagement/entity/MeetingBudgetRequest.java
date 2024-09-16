package com.example.universitymanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "meeting_budget_request")
public class MeetingBudgetRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "club_id", nullable = false)
    private Club club;

    @Enumerated(EnumType.STRING)
    private RequestType type; // e.g., MEETING_REQUEST, BUDGET_REQUEST

    private String details;

    private LocalDateTime requestedAt;

    @Enumerated(EnumType.STRING)
    private RequestStatus status; // e.g., PENDING, APPROVED, REJECTED

    // other fields as needed
}
