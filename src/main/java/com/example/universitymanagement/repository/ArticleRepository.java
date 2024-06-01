package com.example.universitymanagement.repository;

import com.example.universitymanagement.entity.Article;
import com.example.universitymanagement.entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
    List<Article> findByClub(Club club);
}
