package com.example.demo.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.nio.CharBuffer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

@Component
@Getter
@RequiredArgsConstructor
public class Separator {
    private final Pattern engPattern = Pattern.compile("[a-zA-Z]");
    private final Pattern numPattern = Pattern.compile("[0-9]");
    private final StringUtil stringUtil;
    @Setter
    private String eng;
    @Setter
    private String number;

    public Separator separateStringOnlyEngAndNumber(final String html) {
        final char[] htmlChars = html.toCharArray();
        final IntStream asciiStream = CharBuffer.wrap(htmlChars).chars();
        final StringBuilder sb = new StringBuilder();
        asciiStream.filter(AsciiUtil::isEngOrNumber).forEach(ascii -> sb.append((char) ascii));
        final String engAndNumString = sb.toString();
        this.eng = getOnlyEng(engAndNumString);
        this.number = getOnlyNumb(engAndNumString);
        return this;
    }

    public String toMixedString() {
        return stringUtil.mixString(this.eng, this.number);
    }

    private String getOnlyEng(final String text) {
        final Matcher matcher = engPattern.matcher(text);
        final StringBuilder sb = new StringBuilder();
        while (matcher.find()) sb.append(matcher.group());
        return sb.toString();
    }

    private String getOnlyNumb(final String text) {
        final Matcher matcher = numPattern.matcher(text);
        final StringBuilder sb = new StringBuilder();
        while (matcher.find()) {
            sb.append(matcher.group());
        }
        return sb.toString();
    }
}
