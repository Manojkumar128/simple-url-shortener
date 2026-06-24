package com.janavani.backend.service;

import com.janavani.backend.dto.ShortenUrlRequest;
import com.janavani.backend.dto.ShortenUrlResponse;
import com.janavani.backend.model.UrlMapping;
import com.janavani.backend.repository.UrlMappingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UrlShorteningService {
    
    private final UrlMappingRepository urlMappingRepository;
    private static final String CHARSET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SHORT_CODE_LENGTH = 6;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    @Transactional
    public ShortenUrlResponse shortenUrl(ShortenUrlRequest request, String baseUrl) {
        String shortCode = generateUniqueShortCode();
        
        UrlMapping mapping = new UrlMapping();
        mapping.setShortCode(shortCode);
        mapping.setOriginalUrl(request.getOriginalUrl());
        mapping.setClicks(0);
        
        UrlMapping saved = urlMappingRepository.save(mapping);
        
        return buildResponse(saved, baseUrl);
    }
    
    @Transactional(readOnly = true)
    public Optional<String> getOriginalUrl(String shortCode) {
        return urlMappingRepository.findByShortCode(shortCode)
                .map(UrlMapping::getOriginalUrl);
    }
    
    @Transactional
    public void recordClick(String shortCode) {
        urlMappingRepository.findByShortCode(shortCode)
                .ifPresent(mapping -> {
                    mapping.setClicks(mapping.getClicks() + 1);
                    urlMappingRepository.save(mapping);
                });
    }
    
    @Transactional(readOnly = true)
    public Optional<ShortenUrlResponse> getUrlInfo(String shortCode, String baseUrl) {
        return urlMappingRepository.findByShortCode(shortCode)
                .map(mapping -> buildResponse(mapping, baseUrl));
    }
    
    private String generateUniqueShortCode() {
        String shortCode;
        do {
            shortCode = generateRandomShortCode();
        } while (urlMappingRepository.existsByShortCode(shortCode));
        
        return shortCode;
    }
    
    private String generateRandomShortCode() {
        StringBuilder shortCode = new StringBuilder();
        for (int i = 0; i < SHORT_CODE_LENGTH; i++) {
            int index = (int) (Math.random() * CHARSET.length());
            shortCode.append(CHARSET.charAt(index));
        }
        return shortCode.toString();
    }
    
    private ShortenUrlResponse buildResponse(UrlMapping mapping, String baseUrl) {
        String shortUrl = baseUrl + "/api/urls/" + mapping.getShortCode();
        
        return new ShortenUrlResponse(
                mapping.getShortCode(),
                shortUrl,
                mapping.getOriginalUrl(),
                (long) mapping.getClicks(),
                mapping.getCreatedAt().format(formatter)
        );
    }
}
