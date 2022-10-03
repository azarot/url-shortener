package com.example.urlshortener.service;

import javax.transaction.Transactional;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import com.example.urlshortener.controller.OriginalUrl;
import com.example.urlshortener.entity.ShortenedUrl;
import com.example.urlshortener.entity.ShortenedUrlRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShortenerService {
    private final ShortenedUrlRepository repository;


    @Transactional
    public String getShortenedUrl(OriginalUrl originalUrl) {
        return repository.findByOriginalUrl(originalUrl.getUrl())
          .map(ShortenedUrl::getShortUrl)
          .orElseGet(() -> {
              var shortenedUrl = new ShortenedUrl();
              shortenedUrl.setShortUrl(RandomStringUtils.randomAlphabetic(8));
              shortenedUrl.setOriginalUrl(originalUrl.getUrl());
              shortenedUrl.setTitle(originalUrl.getTitle());

              repository.save(shortenedUrl);
              return shortenedUrl.getShortUrl();
          });
    }

    public Optional<String> getOriginalUrl(String shortUrl) {
        return repository.findById(shortUrl)
          .map(ShortenedUrl::getOriginalUrl);
    }
}
