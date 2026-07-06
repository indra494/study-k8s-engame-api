package com.usb.engame.api.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "game_results")
@Getter
@Setter
@NoArgsConstructor
public class GameResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(nullable = false)
    private int score;

    @Column(name = "total_questions", nullable = false)
    private int totalQuestions;

    @Column(name = "correct_count", nullable = false)
    private int correctCount;

    @Column(name = "played_at", nullable = false, updatable = false, insertable = false)
    private Instant playedAt;

    public GameResult(Long userId, int score, int totalQuestions, int correctCount) {
        this.userId = userId;
        this.score = score;
        this.totalQuestions = totalQuestions;
        this.correctCount = correctCount;
    }
}
