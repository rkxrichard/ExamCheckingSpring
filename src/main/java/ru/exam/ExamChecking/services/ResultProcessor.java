package ru.exam.ExamChecking.services;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public interface ResultProcessor {
    int calculateTotalScore(String keyFilePath, String answersFilePath) throws IOException;
    String extractAnswer(String line);

}
