package com.example.demo.application.api;

import com.example.demo.application.service.ParserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/parse")
@Validated
public class ParserRestController {
    private final ParserService parserService;

    @GetMapping("/html")
    public void parseHTMLByURI(@RequestParam
                               @Pattern(regexp = "^((http(s?))\\:\\/\\/)([0-9a-zA-Z\\-]+\\.)+[a-zA-Z]{2,6}(\\:[0-9]+)?(\\/\\S*)?$",
                                       message = "incorrect url")
                                       String url) {
        parserService.parseHTMLByURI(url);
    }
}
