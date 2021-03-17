package com.study.juhee.stringboot.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor // final 필드의 생성자를 생성해줌.
public class HelloResponseDto {

    private final String name;
    private final int amount;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime currentTime;
}
