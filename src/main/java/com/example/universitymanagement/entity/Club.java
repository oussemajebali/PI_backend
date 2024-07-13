package com.example.universitymanagement.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Entity
@Table(name = "_club")
public class Club {

    @Setter
    @Getter
    @Id
    @GeneratedValue
    private int Id;

    @NotBlank(message = "Name is mandatory")

    private String name;
    private String description;

   /* @ManyToOne
    @JoinColumn(name = "club_leader_id", nullable = false) //each club must have a leader
    private User clubLeader;*/

    /*@ManyToMany
    @JoinTable(
            name = "club_members",
            joinColumns = @JoinColumn(name = "club_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> clubMembers;*/

}
