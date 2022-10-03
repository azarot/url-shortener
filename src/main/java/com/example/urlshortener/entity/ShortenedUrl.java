package com.example.urlshortener.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import java.time.Instant;

import lombok.Data;

@Entity()
@Table(name = "shortened_urls")
@Data
public class ShortenedUrl {
    @Id
    @Column(name = "id")
    private String shortUrl;

    private String originalUrl;

    private String title;

    @Column(insertable = false)
    private Instant createdAt;
}
