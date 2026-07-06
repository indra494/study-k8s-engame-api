package com.usb.engame.api.dto;

public record UserProfileDto(
        String nickname,
        long totalScore,
        String tier,
        int rank
) {
}
