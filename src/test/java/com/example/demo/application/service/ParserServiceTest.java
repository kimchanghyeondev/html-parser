package com.example.demo.application.service;

import com.example.demo.application.vo.ParseStringVO;
import com.example.demo.config.RestTemplateConfig;
import com.example.demo.exception.NetworkException;
import com.example.demo.util.HttpRequestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.MatcherAssert.assertThat;


class ParserServiceTest {

    private final HttpRequestUtils httpRequestUtils = new HttpRequestUtils(new RestTemplateConfig().restTemplate());
    private final ParserService parserService = new ParserService(httpRequestUtils);

    @Test
    @DisplayName("URL로요청본문을가져온다")
    public void getResponseBodyStringByURL() {
        String url = "https://naver.com";
        String response = httpRequestUtils.getResponseBodyStringByURL(url);
        assertThat(response.contains("네이버")).isTrue();
    }

    @DisplayName("잘못된 url요청시 NetworkException이 발생한다.")
    @Test
    void failGetResponseBodyStringByURL() {
        assertThatThrownBy(() ->
                httpRequestUtils.getResponseBodyStringByURL("https://www.naver-wrong-fake.com"))
                .isInstanceOf(NetworkException.class);
    }

    @Test
    @DisplayName("문자열을 영문자와 숫자만 파싱한다")
    public void parseStringOnlyEngAndNumber() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = parserService.getClass().getDeclaredMethod("parseStringOnlyEngAndNumber", String.class);
        method.setAccessible(true);
        String responseBody = (String) method.invoke(parserService, "<div>!A$b%%%!한글c</div>");

        assertThat(responseBody, responseBody.equals("divAbcdiv"));
    }

    @Test
    @DisplayName("문자열을 영문자와 숫자만 파싱한다")
    public void toSeparatedEngNumVo() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = parserService.getClass().getDeclaredMethod("toSeparatedEngNumVo", String.class);
        method.setAccessible(true);
        ParseStringVO parseStringVO = (ParseStringVO) method.invoke(parserService, "<div>Ab41한글1c</div>");
        ParseStringVO sameValueVo = new ParseStringVO();
        sameValueVo.setChars("Abcddiivv".toCharArray());
        sameValueVo.setNumbers("114".toCharArray());

        assertThat(parseStringVO.toString(), parseStringVO.equals(sameValueVo));
    }

}