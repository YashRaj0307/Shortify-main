package com.url.url_shortner.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class ClickEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public void setClickDate(LocalDateTime clickDate) {
        this.clickDate = clickDate;
    }

    public void setUrlMapping(UrlMapping urlMapping) {
        this.urlMapping = urlMapping;
    }

    private LocalDateTime clickDate;

    public Long getId() {
        return id;
    }

    public LocalDateTime getClickDate() {
        return clickDate;
    }

    public UrlMapping getUrlMapping() {
        return urlMapping;
    }

    @ManyToOne
    @JoinColumn(name = "url_mapping_id")
    private UrlMapping urlMapping;
}
