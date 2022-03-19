package com.example.demo.application.service;

import com.example.demo.config.RestTemplateConfig;
import com.example.demo.exception.NetworkException;
import com.example.demo.util.HttpRequestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class ParserServiceTest {

    private final HttpRequestUtils httpRequestUtils = new HttpRequestUtils(new RestTemplateConfig().restTemplate());

    @Test
    @DisplayName("URL로요청본문을가져온다")
    public void getResponseBodyStringByURL() {
        String url = "https://naver.com";
        String response = httpRequestUtils.getResponseBodyStringByURL(url);
        assertThat(response.contains("네이버")).isTrue();
    }

    @DisplayName("잘못된 url요청시 NetworkException이 발생한다.")
    @Test
    void failGetResponseBodyStringByURL() {
        assertThatThrownBy(() ->
                httpRequestUtils.getResponseBodyStringByURL("https://www.naver-wrong-fake.com"))
                .isInstanceOf(NetworkException.class);
    }

}