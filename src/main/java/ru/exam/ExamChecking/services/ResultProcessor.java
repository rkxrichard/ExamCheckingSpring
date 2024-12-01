package ru.exam.ExamChecking.services;

import java.io.IOException;

public interface ResultProcessor {
    int calculateTotalScore(String keyFilePath, String answersFilePath) throws IOException;
    String extractAnswer(String line);

}
