package com.url.url_shortner.service;

import com.url.url_shortner.dtos.ClickEventDTO;
import com.url.url_shortner.dtos.UrlMappingDTO;
import com.url.url_shortner.models.ClickEvent;
import com.url.url_shortner.models.UrlMapping;
import com.url.url_shortner.models.User;
import com.url.url_shortner.repository.ClickEventRepository;
import com.url.url_shortner.repository.UrlMappingRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UrlMappingService {

    @Autowired
    private UrlMappingRepository urlMappingRepository;

    @Autowired
    private ClickEventRepository clickEventRepository;

    public UrlMappingDTO createShortUrl(String originalUrl, User user) {
        String shortUrl= generateShortUrl();
        UrlMapping urlMapping=new UrlMapping();
        urlMapping.setOriginalUrl(originalUrl);
        urlMapping.setShortUrl(shortUrl);
        urlMapping.setUser(user);
        urlMapping.setCreatedDate(LocalDateTime.now());
        UrlMapping savedUrlMapping=urlMappingRepository.save(urlMapping);
        return convertToDTO(savedUrlMapping);
    }

    private UrlMappingDTO convertToDTO(UrlMapping urlMapping){
        UrlMappingDTO urlMappingDTO=new UrlMappingDTO();
        urlMappingDTO.setShortUrl(urlMapping.getShortUrl());
        urlMappingDTO.setId(urlMapping.getId());
        urlMappingDTO.setOriginalUrl(urlMapping.getOriginalUrl());
        urlMappingDTO.setClickCount(urlMapping.getClickCount());
        urlMappingDTO.setCreatedDate(urlMapping.getCreatedDate());
        urlMappingDTO.setUsername(urlMapping.getUser().getUsername());
        return urlMappingDTO;
    }



    private String generateShortUrl() {
        String characters="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random=new Random();
        StringBuilder sb=new StringBuilder(8);
        for (int i = 0; i < 8; i++) {
        sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        return sb.toString();
    }

    public List<UrlMappingDTO> getUrlsByUser(User user) {
        return urlMappingRepository.findByUser(user).stream().map(this::convertToDTO).toList();
    }

    public List<ClickEventDTO> getClickEventsByDate(String shortUrl, LocalDateTime start, LocalDateTime end) {
        UrlMapping urlMapping=urlMappingRepository.findByShortUrl(shortUrl);
        if(urlMapping!=null){
            return clickEventRepository.findByUrlMappingAndClickDateBetween(urlMapping, start, end).stream().collect(
                    Collectors.groupingBy(click -> click.getClickDate().toLocalDate(), Collectors.counting()))
                    .entrySet().stream().map(click ->
                    {
                        ClickEventDTO clickEventDTO=new ClickEventDTO();
                        clickEventDTO.setClickDate(click.getKey());
                        clickEventDTO.setCount(click.getValue());
                        return clickEventDTO;
                    })
                    .collect(Collectors.toList());
        }
        return null;
    }

    public Map<LocalDate, Long> getTotalClicksByUserAndDate(User user, LocalDate start, LocalDate end) {
        List<UrlMapping> urlMapping = urlMappingRepository.findByUser(user);
        List<ClickEvent> clickEvents=clickEventRepository.findByUrlMappingInAndClickDateBetween(urlMapping, start.atStartOfDay(), end.plusDays(1).atStartOfDay());
        return clickEvents.stream().collect(
                Collectors.groupingBy(click -> click.getClickDate().toLocalDate(), Collectors.counting()));

    }

    public UrlMapping getOriginalUrl(String shortUrl) {
        UrlMapping urlMapping = urlMappingRepository.findByShortUrl(shortUrl);
        if (urlMapping != null) {
            urlMapping.setClickCount(urlMapping.getClickCount() + 1);
            urlMappingRepository.save(urlMapping);

            // Record Click Event
            ClickEvent clickEvent = new ClickEvent();
            clickEvent.setClickDate(LocalDateTime.now());
            clickEvent.setUrlMapping(urlMapping);
            clickEventRepository.save(clickEvent);
        }

        return urlMapping;
    }
}
