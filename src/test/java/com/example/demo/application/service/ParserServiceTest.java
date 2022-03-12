package com.example.demo.application.service;

import com.example.demo.application.vo.ParseStringVO;
import com.example.demo.config.RestTemplateConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.hamcrest.MatcherAssert.assertThat;


class ParserServiceTest {

    private ParserService parserService = new ParserService(new RestTemplateConfig().restTemplate());


    @Test
    @DisplayName("URL로요청본문을가져온다")
    public void getResponseBodyStringByURL() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String url = "https://naver.com";
        Method method = parserService.getClass().getDeclaredMethod("getResponseBodyStringByURL", String.class);
        method.setAccessible(true);
        String responseBody = (String) method.invoke(parserService, url);

        assertThat(responseBody, responseBody.contains("네이버"));
    }

    @Test
    @DisplayName("html태그제거")
    public void removeHtmlTag() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = parserService.getClass().getDeclaredMethod("removeHtmlTag", String.class);
        method.setAccessible(true);
        String responseBody = (String) method.invoke(parserService, "<div>abc</did>");

        assertThat(responseBody, responseBody.equals("abc"));
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