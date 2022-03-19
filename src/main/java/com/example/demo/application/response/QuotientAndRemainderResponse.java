package com.example.demo.application.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class QuotientAndRemainderResponse {
    private String quotient; // 몫
    private String remainder; // 나머지


    public QuotientAndRemainderResponse(final String text, final int unit) {
        final int bodyLength = text.length();
        if (bodyLength < unit) {
            remainder = text;
            return;
        }
        final int quotientLength = (bodyLength / unit) * unit;
        this.quotient = text.substring(0, quotientLength);
        this.remainder = text.substring(quotientLength);
    }

}
