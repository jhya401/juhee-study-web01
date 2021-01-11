package com.study.juhee.stringboot.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor // final 필드의 생성자를 생성해줌.
public class HelloResponseDto {

    private final String name;
    private final int amount;

}
