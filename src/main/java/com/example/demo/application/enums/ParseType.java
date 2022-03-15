package com.example.demo.application.enums;

import lombok.RequiredArgsConstructor;

import java.util.function.Function;

@RequiredArgsConstructor
public enum ParseType {
    TEXTALL(text -> text),
    EXCLUDEHTML(text -> text.replaceAll(Constants.REGEX_REMOVE_HTML_TAG_PATTERN, ""));

    private final Function<String, String> function;

    public String getParsedText(final String text) {
        return function.apply(text);
    }


    private static class Constants {
        public static final String REGEX_REMOVE_HTML_TAG_PATTERN = "<[^>]*>";
    }
}
