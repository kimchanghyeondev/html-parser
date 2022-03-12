package com.example.demo.application.vo;

import lombok.Data;

import java.util.Arrays;

@Data
public class ParseStringVO {
    private char[] chars;
    private char[] numbers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParseStringVO that = (ParseStringVO) o;
        return Arrays.equals(chars, that.chars) && Arrays.equals(numbers, that.numbers);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(chars);
        result = 31 * result + Arrays.hashCode(numbers);
        return result;
    }

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
