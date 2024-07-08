package com.hh_exam.dto.request;

public record PostRequestDTO(
    String username,
    String title,
    String content,
    int price
) {

}
