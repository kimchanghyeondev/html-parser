package com.example.demo.application.vo;

import lombok.Data;

@Data
public class ParseStringVO {
    private char[] chars;
    private char[] numbers;

    public String toCrossString() {
        if (chars.length > numbers.length) {
            return mergeString(numbers, chars);
        } else {
            return mergeString(chars, numbers);
        }
    }

    private String mergeString(char[] chars, char[] numbers) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            sb.append(chars[i]);
            sb.append(numbers[i]);
        }
        for (int i = numbers.length - chars.length; i < numbers.length; i++) {
            sb.append(numbers[i]);
        }
        return sb.toString();
    }
}
