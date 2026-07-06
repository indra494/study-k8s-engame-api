package com.usb.engame.api.controller;

import com.usb.engame.api.dto.ScoreSubmitRequest;
import com.usb.engame.api.dto.ScoreSubmitResponse;
import com.usb.engame.api.security.CurrentUser;
import com.usb.engame.api.service.ScoreService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/scores")
public class ScoreController {

    private final ScoreService scoreService;

    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @PostMapping
    public ScoreSubmitResponse submit(HttpServletRequest request, @Valid @RequestBody ScoreSubmitRequest body) {
        Long userId = CurrentUser.require(request);
        return scoreService.submit(userId, body);
    }
}
