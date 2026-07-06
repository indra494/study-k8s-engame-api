package com.usb.engame.api.repository;

import com.usb.engame.api.domain.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WordRepository extends JpaRepository<Word, Long> {
}
