package com.hh_exam.dto.response;

public record PostResponseDTO(
    Long id,
    String username,
    String title,
    String content,
    int price
) {

}
