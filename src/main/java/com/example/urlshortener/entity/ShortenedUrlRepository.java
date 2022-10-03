package com.example.urlshortener.entity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ShortenedUrlRepository extends JpaRepository<ShortenedUrl, String> {

    @Query("select s from ShortenedUrl s where s.originalUrl = ?1")
    Optional<ShortenedUrl> findByOriginalUrl(String originalUrl);
}
