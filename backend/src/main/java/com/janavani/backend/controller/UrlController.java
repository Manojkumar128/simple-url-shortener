package com.janavani.backend.controller;

import com.janavani.backend.dto.ShortenUrlRequest;
import com.janavani.backend.dto.ShortenUrlResponse;
import com.janavani.backend.exception.ResourceNotFoundException;
import com.janavani.backend.service.UrlShorteningService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/urls")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class UrlController {
    
    private final UrlShorteningService urlShorteningService;
    
    @PostMapping("/shorten")
    public ResponseEntity<ShortenUrlResponse> shortenUrl(
            @Valid @RequestBody ShortenUrlRequest request,
            HttpServletRequest httpRequest) {
        
        String baseUrl = getBaseUrl(httpRequest);
        ShortenUrlResponse response = urlShorteningService.shortenUrl(request, baseUrl);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping("/{shortCode}")
    public RedirectView redirectToOriginalUrl(@PathVariable String shortCode) {
        urlShorteningService.recordClick(shortCode);
        
        return urlShorteningService.getOriginalUrl(shortCode)
                .map(RedirectView::new)
                .orElseThrow(() -> new ResourceNotFoundException("URL not found with short code: " + shortCode));
    }
    
    @GetMapping("/{shortCode}/info")
    public ResponseEntity<ShortenUrlResponse> getUrlInfo(
            @PathVariable String shortCode,
            HttpServletRequest httpRequest) {
        
        String baseUrl = getBaseUrl(httpRequest);
        
        return urlShorteningService.getUrlInfo(shortCode, baseUrl)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("URL not found with short code: " + shortCode));
    }
    
    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "UP");
        response.put("message", "URL Shortener service is running");
        return ResponseEntity.ok(response);
    }
    
    private String getBaseUrl(HttpServletRequest request) {
        String scheme = request.getScheme();
        String serverName = request.getServerName();
        int serverPort = request.getServerPort();
        
        String url = scheme + "://" + serverName;
        if ((scheme.equals("http") && serverPort != 80) || 
            (scheme.equals("https") && serverPort != 443)) {
            url += ":" + serverPort;
        }
        
        return url;
    }
}
