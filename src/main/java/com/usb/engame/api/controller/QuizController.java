package com.usb.engame.api.controller;

import com.usb.engame.api.dto.QuizQuestionDto;
import com.usb.engame.api.service.QuizService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/quiz")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping
    public List<QuizQuestionDto> getQuiz(@RequestParam(defaultValue = "10") int count) {
        return quizService.generateQuiz(count);
    }
}
