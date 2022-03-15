package com.example.demo.application.api;

import com.example.demo.application.enums.ParseType;
import com.example.demo.application.service.ParserService;
import com.example.demo.application.vo.QuotientAndRemainderResponse;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/parse")
@Validated
public class ParserRestController {
    private final ParserService parserService;

    @GetMapping("/html")
    @ApiOperation(value = "url과 parsingType으로 응답본문을 파싱한다.")
    public QuotientAndRemainderResponse parseHTMLByURI(@RequestParam
                                 @Pattern(regexp = "^((http(s?))\\:\\/\\/)([0-9a-zA-Z\\-]+\\.)+[a-zA-Z]{2,6}(\\:[0-9]+)?(\\/\\S*)?$",
                                         message = "incorrect url")
                                         String url,
                                                       @RequestParam("type") ParseType type,
                                                       @Min (value = 1, message = "can use only positive number") @RequestParam("unit") int unit) {
        return parserService.parseHTMLByURI(url, type, unit);
    }
}
