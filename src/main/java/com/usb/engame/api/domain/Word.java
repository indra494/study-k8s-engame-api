package com.usb.engame.api.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "words")
@Getter
@Setter
@NoArgsConstructor
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String english;

    @Column(name = "korean_pronunciation", nullable = false, length = 50)
    private String koreanPronunciation;

    @Column(name = "korean_meaning", nullable = false, length = 50)
    private String koreanMeaning;

    @Column(nullable = false, length = 30)
    private String category;

    @Column(name = "image_filename", nullable = false, length = 255)
    private String imageFilename;
}
