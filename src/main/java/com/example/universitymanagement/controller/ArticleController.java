package com.example.universitymanagement.controller;

import com.example.universitymanagement.entity.Article;
import com.example.universitymanagement.repository.ArticleRepository;
import com.example.universitymanagement.repository.ClubRepository;
import com.example.universitymanagement.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/article")
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ClubRepository clubRepository;
    @Autowired
    private  ArticleService articleService;

    @GetMapping
    public List<Article> getAllArticles() {
            return articleRepository.findAll();
    }
    @GetMapping("/{Id}")
    public Optional<Article> findArticle(@PathVariable int Id) {
        return articleRepository.findById(Id);
    }
    @GetMapping("/club/{clubId}")
    public List<Article> findArticlesByClub(@PathVariable int clubId) {
        return articleService.getArticlesByClub(clubId);
    }
    @PostMapping("/createArticle")
    @PreAuthorize("hasRole('UNIVERSITY_ADMIN') or hasRole('CLUB_LEADER')")
    public Article createArticle(@RequestBody Article article) {
        return articleRepository.save(article);
    }

    @PutMapping("/updateArticle/{id}")
    @PreAuthorize("hasRole('UNIVERSITY_ADMIN') or hasRole('CLUB_LEADER')")
    public ResponseEntity<Article> updateArticle(@PathVariable int id, @RequestBody Article updatedArticle) {
        return articleRepository.findById(id)
                .map(article -> {
                    article.setTitle(updatedArticle.getTitle());
                    article.setContent(updatedArticle.getContent());
                    article.setPrice(updatedArticle.getPrice());
                    article.setContent(updatedArticle.getContent());
                    article.setPhotoUrl(updatedArticle.getPhotoUrl());
                    Article savedArticle = articleRepository.save(article);
                    return ResponseEntity.ok(savedArticle);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/deleteArticle/{id}")
    @PreAuthorize("hasRole('UNIVERSITY_ADMIN') or hasRole('CLUB_LEADER')")
    public ResponseEntity<Void> deleteArticle(@PathVariable int id) {
        articleRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
