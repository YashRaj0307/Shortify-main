package com.url.url_shortner.repository;

import com.url.url_shortner.models.ClickEvent;
import com.url.url_shortner.models.UrlMapping;
import com.url.url_shortner.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ClickEventRepository extends JpaRepository<ClickEvent,Long> {
    List<ClickEvent> findByUrlMappingAndClickDateBetween(UrlMapping mapping, LocalDateTime startDate,LocalDateTime endDate);
    List<ClickEvent> findByUrlMappingInAndClickDateBetween(List<UrlMapping> mapping, LocalDateTime startDate,LocalDateTime endDate);
}
