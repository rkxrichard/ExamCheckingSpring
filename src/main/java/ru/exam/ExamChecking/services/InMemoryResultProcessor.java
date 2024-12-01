package ru.exam.ExamChecking.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryResultProcessor implements ResultProcessor{

    private final int pointsForGroup1;
    private final int pointsForGroup2;
    private final int pointsForGroup3;


    public InMemoryResultProcessor(int pointsForGroup1, int pointsForGroup2, int pointsForGroup3) {
        this.pointsForGroup1 = pointsForGroup1;
        this.pointsForGroup2 = pointsForGroup2;
        this.pointsForGroup3 = pointsForGroup3;
    }

    @Override
    public int calculateTotalScore(String keyFilePath, String answersFilePath) throws IOException {
        List<String> keys = Files.readAllLines(Paths.get(keyFilePath))
                .stream()
                .map(this::extractAnswer)
                .collect(Collectors.toList());

        List<String> answers = Files.readAllLines(Paths.get(answersFilePath))
                .stream()
                .map(this::extractAnswer)
                .collect(Collectors.toList());

        if (keys.size() != answers.size()) {
            throw new IllegalArgumentException("Number of keys does not match number of answers");
        }

        int totalScore = 0;

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String answer = answers.get(i);

            if (key.equals(answer)) {
                if (i < 3) {
                    totalScore += pointsForGroup1;
                } else if (i < 7) {
                    totalScore += pointsForGroup2;
                } else {
                    totalScore += pointsForGroup3;
                }
            }
        }

        return totalScore;
    }

    @Override
    public String extractAnswer(String line) {
        String[] parts = line.split("-");
        return parts.length > 1 ? parts[1].trim() : "";
    }
}
