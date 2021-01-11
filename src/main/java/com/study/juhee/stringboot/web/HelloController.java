package com.study.juhee.stringboot.web;

import com.study.juhee.stringboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // JSON을 반환하는 컨트롤러로 만들어줌(예전의 @ResponseBody의 기능)
public class HelloController {

    // 실습01. 컨트롤러 실행해보기
    @GetMapping("/hello")   // 예전에는 @RequestMapping(method = RequestMethod.GET와 동일.
    public String hello() {
        return "hello";
    }
    
    // 실습02. 롬복을 이용해서 DTO객체 넘기기
    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount);
    }
}
