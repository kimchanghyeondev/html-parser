package com.example.demo.application.service;


import com.example.demo.application.enums.ParseType;
import com.example.demo.application.vo.ParseStringVO;
import com.example.demo.exception.EmptyBodyException;
import com.example.demo.exception.NetworkException;
import com.example.demo.util.AsciiUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.nio.CharBuffer;
import java.util.HashMap;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class ParserService {
    private final RestTemplate restTemplate;

    public String parseHTMLByURI(String url, ParseType type) {
        ResponseEntity<String> response = getResponseByURL(url);
        validationHttpStatus(response);
        String responseBody = response.getBody();
        if (type.equals(ParseType.EXCLUDEHTML)) responseBody = removeHtmlTag(responseBody);
        String parsedBody = parseStringOnlyEngAndNumber(responseBody);
        ParseStringVO parseStringVO = orderByAlphabetDesc(parsedBody);
        return parseStringVO.toCrossString();
    }

    private String removeHtmlTag(String responseBody) {
        return responseBody.replaceAll("<[^>]*>", "").replaceAll("\n", "").trim();
    }

    private ParseStringVO orderByAlphabetDesc(String parsedBody) {
        char[] htmlChars = parsedBody.toCharArray();
        IntStream asciiStream = CharBuffer.wrap(htmlChars).chars();
        HashMap<Integer, Integer> map = new HashMap<>();
        asciiStream.filter(AsciiUtil::isEngOrNumber).forEach(ascii -> {
            Integer value = map.getOrDefault(ascii, 0);
            map.put(ascii, value + 1);
        });

        StringBuilder sb = new StringBuilder();
        ParseStringVO parseStringVO = new ParseStringVO();

        //65 ~ 90
        for (int i = 65; i <= 90; i++) {
            Integer upperAmount = map.get(i);
            Integer lowerAmount = map.get(i + 32);
            if (ObjectUtils.isEmpty(upperAmount)) continue;
            for (int j = 0; j < upperAmount; j++) {
                sb.append((char) i);
            }
            if (ObjectUtils.isEmpty(lowerAmount)) continue;
            for (int j = 0; j < lowerAmount; j++) {
                sb.append((char) (i + 32));
            }
        }

        parseStringVO.setChars(sb.toString().toCharArray());
        sb.delete(0, sb.length());
        //48 ~57
        for (int i = 48; i <= 57; i++) {
            Integer numberAmount = map.get(i);
            if (ObjectUtils.isEmpty(numberAmount)) continue;
            for (int j = 0; j < numberAmount; j++) {
                sb.append((char) i);
            }
        }
        parseStringVO.setNumbers(sb.toString().toCharArray());
        return parseStringVO;
    }

    private String parseStringOnlyEngAndNumber(String html) {
        char[] htmlChars = html.toCharArray();
        IntStream asciiStream = CharBuffer.wrap(htmlChars).chars();
        StringBuilder sb = new StringBuilder();
        asciiStream.filter(AsciiUtil::isEngOrNumber).forEach(ascii -> sb.append((char) ascii));
        return sb.toString();
    }

    private static HttpEntity getApplicationJsonHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");
        return new HttpEntity(headers);
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
}
