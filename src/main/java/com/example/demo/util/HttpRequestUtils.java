package com.example.demo.util;

import com.example.demo.exception.EmptyBodyException;
import com.example.demo.exception.NetworkException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class HttpRequestUtils {
    private final RestTemplate restTemplate;

    public String getResponseBodyStringByURL(String url) {
        ResponseEntity<String> response = getResponseByURL(url);
        validationHttpStatus(response);
        return response.getBody();
    }

    private ResponseEntity<String> getResponseByURL(String url) {
        try {
            return restTemplate.exchange(url, HttpMethod.GET, getApplicationJsonHttpEntity(), String.class);
        } catch (ResourceAccessException e) {
            throw new NetworkException(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.toString());
        }
    }

    private void validationHttpStatus(ResponseEntity<String> response) {
        HttpStatus statusCode = response.getStatusCode();
        if (!statusCode.is2xxSuccessful()) {
            throw new NetworkException(response.getStatusCodeValue(), response.getBody());
        }

        if (ObjectUtils.isEmpty(response.getBody())) {
            throw new EmptyBodyException(HttpStatus.NO_CONTENT.value(), HttpStatus.NO_CONTENT.toString());
        }
    }

    private static HttpEntity getApplicationJsonHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        return new HttpEntity(headers);
    }
}
