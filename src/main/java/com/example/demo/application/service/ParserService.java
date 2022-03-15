package com.example.demo.application.service;


import com.example.demo.application.enums.ParseType;
import com.example.demo.application.vo.ParseStringVO;
import com.example.demo.application.vo.QuotientAndRemainderResponse;
import com.example.demo.exception.EmptyBodyException;
import com.example.demo.exception.NetworkException;
import com.example.demo.util.AsciiUtil;
import com.example.demo.util.HttpRequestUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.nio.CharBuffer;
import java.util.HashMap;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class ParserService {
    private final HttpRequestUtils httpRequestUtils;

    public QuotientAndRemainderResponse parseHTMLByURI(final String url,final ParseType type,final int unit) {
        final String responseBody = httpRequestUtils.getResponseBodyStringByURL(url);
        final String parsedResponseBody = type.getParsedText(responseBody);
        final String parsedBody = parseStringOnlyEngAndNumber(parsedResponseBody);
        ParseStringVO parseStringVO = toSeparatedEngNumVo(parsedBody);
        return new QuotientAndRemainderResponse(parseStringVO, unit);
    }


    private ParseStringVO toSeparatedEngNumVo(String parsedBody) {
        char[] htmlChars = parsedBody.toCharArray();
        IntStream asciiStream = CharBuffer.wrap(htmlChars).chars();
        HashMap<Integer, Integer> map = new HashMap<>();
        asciiStream.filter(AsciiUtil::isEngOrNumber).forEach(ascii -> {
            Integer value = map.getOrDefault(ascii, 0);
            map.put(ascii, value + 1);
        });

        StringBuilder sb = new StringBuilder();
        ParseStringVO parseStringVO = new ParseStringVO();

        //65 ~ 90
        for (int i = 65; i <= 90; i++) {
            Integer upperAmount = map.get(i);
            Integer lowerAmount = map.get(i + 32);
            if (ObjectUtils.isEmpty(upperAmount)) {
                if (ObjectUtils.isEmpty(lowerAmount)) continue;
                for (int j = 0; j < lowerAmount; j++) { // 대문자는 없고 소문자만 있는경우 A->X , a->O 소문자만 붙힌다.
                    sb.append((char) (i + 32));
                }
            } else { // 대문자가 있는경우
                for (int j = 0; j < upperAmount; j++) {
                    sb.append((char) i);
                }
                if (ObjectUtils.isEmpty(lowerAmount)) continue;
                for (int j = 0; j < lowerAmount; j++) {
                    sb.append((char) (i + 32));
                }
            }
        }

        parseStringVO.setChars(sb.toString().toCharArray());
        sb.delete(0, sb.length());
        //48 ~57 numbers
        for (int i = 48; i <= 57; i++) {
            Integer numberAmount = map.get(i);
            if (ObjectUtils.isEmpty(numberAmount)) continue;
            for (int j = 0; j < numberAmount; j++) {
                sb.append((char) i);
            }
        }
        parseStringVO.setNumbers(sb.toString().toCharArray());
        return parseStringVO;
    }

    private String parseStringOnlyEngAndNumber(String html) {
        char[] htmlChars = html.toCharArray();
        IntStream asciiStream = CharBuffer.wrap(htmlChars).chars();
        StringBuilder sb = new StringBuilder();
        asciiStream.filter(AsciiUtil::isEngOrNumber).forEach(ascii -> sb.append((char) ascii));
        return sb.toString();
    }



}
