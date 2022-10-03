package com.example.urlshortener.controller;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.urlshortener.service.ShortenerService;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RestController
@RequestMapping("short")
@RequiredArgsConstructor
public class ShortenerController {
    private final ShortenerService shortenerService;

    @PostMapping
    public void getShort(@RequestBody OriginalUrl originalUrl, HttpServletResponse response) {
        response.setHeader("Location", shortenerService.getShortenedUrl(originalUrl));
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    @GetMapping("/{shortUrl}")
    @SneakyThrows
    public ResponseEntity<Object> getLong(@PathVariable String shortUrl) {
        return shortenerService.getOriginalUrl(shortUrl)
          .map(url -> ResponseEntity.status(HttpStatus.FOUND).location(URI.create(url)).build())
          .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
