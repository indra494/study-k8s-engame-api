package com.usb.engame.api.dto;

public record LeaderboardEntryDto(
        int rank,
        String nickname,
        long totalScore,
        String tier
) {
}
