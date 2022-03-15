package com.example.demo.application.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ParseTypeTest {

    private String html = "<div>test<div>";

    @DisplayName("EXCLUDEHTML 인 경우 html 태그를 제거한다")
    @Test
    public void excludeHtml() {
        final String parsedText = ParseType.EXCLUDEHTML.getParsedText(html);
        assertThat(parsedText).isEqualTo("test");
    }

    @DisplayName("TEXTALL 인 경우 html 태그를 모두 가져온다")
    @Test
    public void textAll() {
        final String parsedText = ParseType.TEXTALL.getParsedText(html);
        assertThat(parsedText).isEqualTo(html);
    }

}