package com.example.demo.application.vo;

import lombok.Data;

@Data
public class QuotientAndRemainderResponse {
    private String quotient;
    private String remainder;
    private int bodyLength;

    public QuotientAndRemainderResponse(ParseStringVO vo, int unit) {
        String s = vo.toCrossString();
        bodyLength = s.length();
        if (bodyLength < unit) {
            remainder = s;
            return;
        }
        int quotientLength = (bodyLength / unit) * unit;
        int remainderLength = bodyLength - quotientLength;
        quotient = s.substring(0, quotientLength - 1);
        if (remainderLength != 0) {
            remainder = s.substring(quotientLength, quotientLength + remainderLength);
        }
    }

}
