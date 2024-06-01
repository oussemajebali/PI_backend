package com.example.universitymanagement.controller;

import com.example.universitymanagement.entity.Article;
import com.example.universitymanagement.entity.Club;
import com.example.universitymanagement.repository.ArticleRepository;
import com.example.universitymanagement.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/article")
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ClubRepository clubRepository;

    @GetMapping
    public List<Article> getAllArticles(@RequestParam(required = false) int clubId) {
        if (clubId != 0) {
            Club club = clubRepository.findById(clubId).orElse(null);
            return articleRepository.findByClub(club);
        } else {
            return articleRepository.findAll();
        }
    }

    @PostMapping
    @PreAuthorize("hasRole('CLUB_LEADER')")
    public Article createArticle(@RequestBody Article article) {
        return articleRepository.save(article);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('CLUB_LEADER')")
    public Article updateArticle(@PathVariable int id, @RequestBody Article updatedArticle) {
        return articleRepository.findById(id)
                .map(article -> {
                    article.setTitle(updatedArticle.getTitle());
                    article.setContent(updatedArticle.getContent());
                    return articleRepository.save(article);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
