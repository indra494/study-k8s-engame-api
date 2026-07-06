package com.usb.engame.api.dto;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record ScoreSubmitRequest(
        @NotEmpty List<AnswerDto> answers
) {
}
