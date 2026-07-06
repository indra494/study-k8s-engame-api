package com.usb.engame.api.service;

import com.usb.engame.api.domain.Word;
import com.usb.engame.api.dto.QuizOptionDto;
import com.usb.engame.api.dto.QuizQuestionDto;
import com.usb.engame.api.repository.WordRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class QuizService {

    private static final int OPTIONS_PER_QUESTION = 4;

    private final WordRepository wordRepository;

    public QuizService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    public List<QuizQuestionDto> generateQuiz(int count) {
        List<Word> allWords = wordRepository.findAll();
        if (allWords.size() < OPTIONS_PER_QUESTION) {
            throw new IllegalStateException("단어 데이터가 부족합니다.");
        }

        Random random = new Random();
        List<Word> shuffled = new ArrayList<>(allWords);
        Collections.shuffle(shuffled, random);
        int questionCount = Math.min(count, shuffled.size());
        List<Word> questionWords = shuffled.subList(0, questionCount);

        List<QuizQuestionDto> result = new ArrayList<>();
        for (Word correct : questionWords) {
            List<Word> pool = new ArrayList<>(allWords);
            pool.remove(correct);
            Collections.shuffle(pool, random);
            List<Word> distractors = pool.subList(0, OPTIONS_PER_QUESTION - 1);

            List<QuizOptionDto> options = new ArrayList<>();
            options.add(new QuizOptionDto(correct.getId(), correct.getEnglish(), correct.getKoreanPronunciation()));
            for (Word d : distractors) {
                options.add(new QuizOptionDto(d.getId(), d.getEnglish(), d.getKoreanPronunciation()));
            }
            Collections.shuffle(options, random);

            result.add(new QuizQuestionDto(correct.getId(), "/images/words/" + correct.getImageFilename(), options));
        }
        return result;
    }
}
