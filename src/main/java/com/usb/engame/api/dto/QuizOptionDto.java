package com.usb.engame.api.dto;

public record QuizOptionDto(
        Long wordId,
        String english,
        String koreanPronunciation
) {
}
