package com.example.demo.util;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StringUtilTest {

    private final StringUtil stringUtil = new StringUtil();

    @DisplayName("캐릭터배열 2개를 입력받아 하나의 문자열로 섞어준다")
    @Test
    public void mergeString() {
        final String text1 = "TESTSTR";
        final String text2 = "7531444444";
        assertThat(stringUtil.mixString(text1, text2)).isEqualTo("T7E5S3T1S4T4R4444");
    }

}