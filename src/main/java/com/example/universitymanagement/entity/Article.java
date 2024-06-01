package com.example.universitymanagement.entity;

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
@Table(name = "_article")
public class Article {

    @Id
    @GeneratedValue
    private int aritcle_Id;
    private String title;
    private String content;


//    @ManyToOne
//    private Club club;
}
