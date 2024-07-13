package com.example.universitymanagement.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.universitymanagement.entity.*;
import com.example.universitymanagement.repository.ArticleRepository;
import com.example.universitymanagement.repository.ClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;
    private  Cloudinary cloudinary;
    private ClubRepository clubRepository;
    @PreAuthorize("permitAll()")
    public List<Article> getArtByClub(Club club) {
        return articleRepository.findByClub(club);
    }
    @PreAuthorize("permitAll()")
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }
    @PreAuthorize("hasRole('UNIVERSITY_ADMIN') or hasRole('CLUB_LEADER')")
    public Article saveArticle(Article article) {
        return articleRepository.save(article);
    }
    @PreAuthorize("hasRole('UNIVERSITY_ADMIN') or hasRole('CLUB_LEADER')")
    public void deleteArticle(int id) {
        articleRepository.deleteById(id);
    }
    @PreAuthorize("hasRole('UNIVERSITY_ADMIN') or hasRole('CLUB_LEADER')")
    public Article updateArticle(int id, Article articleDtl) {
        Article article = articleRepository.findById(id).orElse(null);
        if (article != null) {
            article.setClub(articleDtl.getClub());
            article.setTitle(articleDtl.getTitle());
            article.setPrice(articleDtl.getPrice());
            article.setContent(articleDtl.getContent());
            article.setPhotoUrl(articleDtl.getPhotoUrl());
            return articleRepository.save(article);
        }
        return null;
    }
    @PreAuthorize("permitAll()")
    public List<Article> getArticlesByClub(int clubId) {
        Optional<Club> clubOptional = clubRepository.findById(clubId);
        if (clubOptional.isPresent()) {
            Club club = clubOptional.get();
            return articleRepository.findByClub(club);
        }
        return List.of();
    }
}
