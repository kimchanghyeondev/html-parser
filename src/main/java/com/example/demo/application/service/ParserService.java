package com.example.demo.application.service;


import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ParserService {
    private final RestTemplate restTemplate;

    public void parseHTMLByURI(String url) {
        HttpEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, getApplicationJsonHttpEntity(), String.class);
        HttpStatus statusCode = ((ResponseEntity) response).getStatusCode();
        String body = response.getBody();
    }

    private static HttpEntity getApplicationJsonHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        return new HttpEntity(headers);
    }
}
