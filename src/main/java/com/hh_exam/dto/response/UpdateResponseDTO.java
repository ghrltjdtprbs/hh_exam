package com.hh_exam.dto.response;

public record UpdateResponseDTO(
    Long id,
    String title,
    String content,
    int price,
    String username
) {

}
