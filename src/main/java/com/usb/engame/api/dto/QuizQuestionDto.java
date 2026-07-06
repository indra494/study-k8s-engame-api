package com.usb.engame.api.dto;

import java.util.List;

public record QuizQuestionDto(
        Long questionId,
        String imageUrl,
        List<QuizOptionDto> options
) {
}
