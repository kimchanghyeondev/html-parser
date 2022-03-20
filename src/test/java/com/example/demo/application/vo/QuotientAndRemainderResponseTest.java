package com.example.demo.application.vo;

import com.example.demo.application.response.QuotientAndRemainderResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

class QuotientAndRemainderResponseTest {

    @DisplayName("몫과 나머지 전체 길이를 가지는 객체를 생성한다.")
    @Test
    public void newQuotientAndRemainderResponse() {
        QuotientAndRemainderResponse response1 = new QuotientAndRemainderResponse("ABCDEF", 3);
        QuotientAndRemainderResponse response2 = new QuotientAndRemainderResponse("ABCDEFG", 2);
        assertAll(
                () -> assertThat(response1.getQuotient()).isEqualTo("ABCDEF"),
                () -> assertThat(response1.getRemainder()).isEqualTo(""),
                () -> assertThat(response2.getQuotient()).isEqualTo("ABCDEF"),
                () -> assertThat(response2.getRemainder()).isEqualTo("G")
        );
    }

}