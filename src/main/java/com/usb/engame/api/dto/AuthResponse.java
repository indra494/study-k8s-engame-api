package com.usb.engame.api.dto;

public record AuthResponse(
        String token,
        Long userId,
        String nickname
) {
}
