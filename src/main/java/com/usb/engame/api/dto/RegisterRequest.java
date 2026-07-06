package com.usb.engame.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank @Size(min = 3, max = 30) String username,
        @NotBlank @Size(min = 4, max = 100) String password,
        @NotBlank @Size(min = 1, max = 30) String nickname
) {
}
