package com.usb.engame.api.service;

import com.usb.engame.api.domain.GameResult;
import com.usb.engame.api.domain.Tier;
import com.usb.engame.api.domain.User;
import com.usb.engame.api.dto.AnswerDto;
import com.usb.engame.api.dto.ScoreSubmitRequest;
import com.usb.engame.api.dto.ScoreSubmitResponse;
import com.usb.engame.api.repository.GameResultRepository;
import com.usb.engame.api.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ScoreService {

    private static final int POINTS_PER_CORRECT = 10;

    private final UserRepository userRepository;
    private final GameResultRepository gameResultRepository;

    public ScoreService(UserRepository userRepository, GameResultRepository gameResultRepository) {
        this.userRepository = userRepository;
        this.gameResultRepository = gameResultRepository;
    }

    @Transactional
    public ScoreSubmitResponse submit(Long userId, ScoreSubmitRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        int correctCount = 0;
        for (AnswerDto answer : request.answers()) {
            if (answer.questionId().equals(answer.selectedWordId())) {
                correctCount++;
            }
        }
        int totalQuestions = request.answers().size();
        int score = correctCount * POINTS_PER_CORRECT;

        gameResultRepository.save(new GameResult(userId, score, totalQuestions, correctCount));

        user.setTotalScore(user.getTotalScore() + score);
        userRepository.save(user);

        Tier tier = Tier.fromScore(user.getTotalScore());
        return new ScoreSubmitResponse(score, correctCount, totalQuestions, user.getTotalScore(), tier.getDisplayName());
    }
}
