package com.usb.engame.api.service;

import com.usb.engame.api.domain.Tier;
import com.usb.engame.api.domain.User;
import com.usb.engame.api.dto.LeaderboardEntryDto;
import com.usb.engame.api.dto.UserProfileDto;
import com.usb.engame.api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeaderboardService {

    private final UserRepository userRepository;

    public LeaderboardService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<LeaderboardEntryDto> getLeaderboard(int limit) {
        List<User> ranked = userRepository.findAllByOrderByTotalScoreDesc();
        List<LeaderboardEntryDto> result = new ArrayList<>();
        for (int i = 0; i < ranked.size() && i < limit; i++) {
            User u = ranked.get(i);
            result.add(new LeaderboardEntryDto(i + 1, u.getNickname(), u.getTotalScore(),
                    Tier.fromScore(u.getTotalScore()).getDisplayName()));
        }
        return result;
    }

    public UserProfileDto getProfile(Long userId) {
        List<User> ranked = userRepository.findAllByOrderByTotalScoreDesc();
        for (int i = 0; i < ranked.size(); i++) {
            User u = ranked.get(i);
            if (u.getId().equals(userId)) {
                return new UserProfileDto(u.getNickname(), u.getTotalScore(),
                        Tier.fromScore(u.getTotalScore()).getDisplayName(), i + 1);
            }
        }
        throw new IllegalArgumentException("사용자를 찾을 수 없습니다.");
    }
}
