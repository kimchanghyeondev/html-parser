package com.example.demo.util;

import org.springframework.stereotype.Component;

import java.nio.CharBuffer;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.IntStream;

@Component
public class Sorter {
    public String sortEng(final String text) {
        final HashMap<Integer, Integer> map = getAsciiAmountMapByText(text);
        final StringBuilder sb = new StringBuilder();
        for (int i = 65; i <= 90; i++) { // 대문자 ascii
            final int upperAmount = Optional.ofNullable(map.get(i)).orElse(0);
            final int lowerAmount = Optional.ofNullable(map.get(i + 32)).orElse(0);
            for (int i1 = 0; i1 < upperAmount; i1++) {
                sb.append((char) i);
            }
            for (int i1 = 0; i1 < lowerAmount; i1++) {
                sb.append((char) (i + 32));
            }
        }
        return sb.toString();
    }


    public String sortNumber(final String text) {
        final HashMap<Integer, Integer> map = getAsciiAmountMapByText(text);
        final StringBuilder sb = new StringBuilder();
        for (int i = 48; i <= 57; i++) {
            final int numberAmount = Optional.ofNullable(map.get(i)).orElse(0);
            for (int j = 0; j < numberAmount; j++) {
                sb.append((char) i);
            }
        }
        return sb.toString();
    }

    public Separator sortSeparator(Separator separator) {
        separator.setEng(sortEng(separator.getEng()));
        separator.setNumber(sortNumber(separator.getNumber()));
        return separator;
    }

    private HashMap<Integer, Integer> getAsciiAmountMapByText(String text) {
        final char[] htmlChars = text.toCharArray();
        final IntStream asciiStream = CharBuffer.wrap(htmlChars).chars();
        final HashMap<Integer, Integer> map = new HashMap<>();
        asciiStream.filter(AsciiUtil::isEngOrNumber).forEach(ascii -> {
            Integer value = map.getOrDefault(ascii, 0);
            map.put(ascii, value + 1);
        });
        return map;
    }

}
