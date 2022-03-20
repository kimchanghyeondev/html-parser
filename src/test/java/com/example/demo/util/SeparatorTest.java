package com.example.demo.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class SeparatorTest {
    private final Separator separator = new Separator();

    @DisplayName("문자열에서 영문자와 숫자를 분리하여 추출한다.")
    @Test
    public void getEngAndNum() {
        final String text = "Str4ing한글st1ring";
        Separator separator = this.separator.separateStringOnlyEngAndNumber(text);
        assertThat(separator.getEng()).isEqualTo("Stringstring");
        assertAll(
                () -> assertThat(separator.getEng()).isEqualTo("Stringstring"),
                () -> assertThat(separator.getNumber()).isEqualTo("41")
        );
    }
}