package com.example.demo.util;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Iterator;

@Component
public class StringUtil {
    public String mixString(String str1, String str2) {
        final StringBuilder sb = new StringBuilder();
        final Iterator<String> iterator1 = Arrays.stream(str1.split("")).iterator();
        final Iterator<String> iterator2 = Arrays.stream(str2.split("")).iterator();

        while (iterator1.hasNext() || iterator2.hasNext()) {
            if (iterator1.hasNext()) {
                sb.append(iterator1.next());
            }

            if (iterator2.hasNext()) {
                sb.append(iterator2.next());
            }
        }
        return sb.toString();
    }
}
