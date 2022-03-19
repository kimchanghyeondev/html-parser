package com.example.demo.application.service;


import com.example.demo.application.enums.ParseType;
import com.example.demo.application.vo.QuotientAndRemainderResponse;
import com.example.demo.util.HttpRequestUtils;
import com.example.demo.util.Separator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParserService {
    private final HttpRequestUtils httpRequestUtils;
    private final Separator separator;

    public QuotientAndRemainderResponse parseHTMLByURI(final String url, final ParseType type, final int unit) {
        final String responseBody = this.httpRequestUtils.getResponseBodyStringByURL(url);
        final String parsedResponseBody = type.getParsedText(responseBody);
        final Separator separator = this.separator.separateStringOnlyEngAndNumber(parsedResponseBody);
        return null;
    }

}
