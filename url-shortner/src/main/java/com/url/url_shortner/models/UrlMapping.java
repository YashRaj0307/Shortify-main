package com.url.url_shortner.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UrlMapping {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String originalUrl;
    private String shortUrl;
    private int clickCount;
    private LocalDateTime createdDate;

    public void setId(Long id) {
        this.id = id;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public void setClickCount(int clickCount) {
        this.clickCount = clickCount;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setClickEvents(List<ClickEvent> clickEvents) {
        this.clickEvents = clickEvents;
    }

    public User getUser() {
        return user;
    }

    public Long getId() {
        return id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public int getClickCount() {
        return clickCount;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public List<ClickEvent> getClickEvents() {
        return clickEvents;
    }

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy="urlMapping")
    private List<ClickEvent> clickEvents;
}
