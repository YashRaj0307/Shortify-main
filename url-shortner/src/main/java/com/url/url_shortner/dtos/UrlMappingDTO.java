package com.url.url_shortner.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UrlMappingDTO {
    private String shortUrl;
    private Long id;
    private String originalUrl;
    private int clickCount;
    private LocalDateTime createdDate;
    private String username;

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public void setClickCount(int clickCount) {
        this.clickCount = clickCount;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public Long getId() {
        return id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public int getClickCount() {
        return clickCount;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public String getUsername() {
        return username;
    }
}
