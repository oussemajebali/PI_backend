package com.example.universitymanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_club")
public class Club {
    @Id
    @GeneratedValue
    private int clubId;
    private String name;

    @OneToMany(mappedBy = "club")
    private List<Article> articles;
}
