package com.usb.engame.api.dto;

public record ScoreSubmitResponse(
        int score,
        int correctCount,
        int totalQuestions,
        long newTotalScore,
        String tier
) {
}
