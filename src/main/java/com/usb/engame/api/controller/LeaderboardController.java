package com.usb.engame.api.controller;

import com.usb.engame.api.dto.LeaderboardEntryDto;
import com.usb.engame.api.dto.UserProfileDto;
import com.usb.engame.api.security.CurrentUser;
import com.usb.engame.api.service.LeaderboardService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LeaderboardController {

    private final LeaderboardService leaderboardService;

    public LeaderboardController(LeaderboardService leaderboardService) {
        this.leaderboardService = leaderboardService;
    }

    @GetMapping("/leaderboard")
    public List<LeaderboardEntryDto> getLeaderboard(@RequestParam(defaultValue = "50") int limit) {
        return leaderboardService.getLeaderboard(limit);
    }

    @GetMapping("/users/me")
    public UserProfileDto getMyProfile(HttpServletRequest request) {
        Long userId = CurrentUser.require(request);
        return leaderboardService.getProfile(userId);
    }
}
