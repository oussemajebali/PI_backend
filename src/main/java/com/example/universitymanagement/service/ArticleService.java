package com.example.universitymanagement.service;

import com.example.universitymanagement.entity.Article;
import com.example.universitymanagement.entity.Club;
import com.example.universitymanagement.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> getArticlesByClub(Club club) {
        return articleRepository.findByClub(club);
    }

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public Article saveArticle(Article article) {
        return articleRepository.save(article);
    }
}
