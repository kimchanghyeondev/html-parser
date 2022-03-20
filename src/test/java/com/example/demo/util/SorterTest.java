package com.example.demo.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class SorterTest {
    private final Sorter sorter = new Sorter();

    @DisplayName("알파벳을 순서대로 정렬한다 같은 알파벳은 대문자가 앞에 출력된다.")
    @Test
    public void sortEng() {
        final String text = "aBbAdFh";
        assertThat(sorter.sortEng(text)).isEqualTo("AaBbdFh");
    }

    @DisplayName("숫자문자열을 정렬한다.")
    @Test
    public void sortNumber() {
        final String text = "4417591";
        assertThat(sorter.sortNumber(text)).isEqualTo("1144579");
    }
}