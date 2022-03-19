package com.example.demo.application.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class QuotientAndRemainderResponse {
    private String quotient; // 몫
    private String remainder; // 나머지
    private int bodyLength; // 전체 길이

    public QuotientAndRemainderResponse(String text, int unit) {
        bodyLength = text.length();
        if (bodyLength < unit) {
            remainder = text;
            return;
        }
        int quotientLength = (bodyLength / unit) * unit;
        quotient = text.substring(0, quotientLength);
        remainder = text.substring(quotientLength);
    }

}
