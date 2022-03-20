package com.example.demo.application.service;


import com.example.demo.application.enums.ParseType;
import com.example.demo.application.response.QuotientAndRemainderResponse;
import com.example.demo.util.HttpRequestUtils;
import com.example.demo.util.Separator;
import com.example.demo.util.Sorter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParserService {
    private final HttpRequestUtils httpRequestUtils;
    private final Separator separator;
    private final Sorter sorter;

    public QuotientAndRemainderResponse parseHTMLByURI(final String url, final ParseType type, final int unit) {
        final String responseBody = this.httpRequestUtils.getResponseBodyStringByURL(url);
        final String parsedResponseBody = type.getParsedText(responseBody);
        final Separator separator = this.separator.separateStringOnlyEngAndNumber(parsedResponseBody);
        final Separator sortedSeparator = sorter.sortSeparator(separator);
        return new QuotientAndRemainderResponse(sortedSeparator.toMixedString(), unit);
    }

}
