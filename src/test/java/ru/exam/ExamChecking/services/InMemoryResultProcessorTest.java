package com.example.processor;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.exam.ExamChecking.services.InMemoryResultProcessor;
import ru.exam.ExamChecking.services.ResultProcessor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class ResultsProcessorTest {

    private ResultProcessor processor;

    @BeforeEach
    void setUp() {
        processor = new InMemoryResultProcessor(1, 2, 3);
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(Paths.get("key.txt"));
        Files.deleteIfExists(Paths.get("answers.txt"));
    }

    @Test
    void testCalculateTotalScore() throws IOException {
        // Подготовка данных
        String keyFileContent = "1 - A\n2 - B\n3 - C\n4 - D\n5 - E\n6 - F\n7 - G\n8 - H\n9 - I\n10 - J";
        String answersFileContent = "1 - A\n2 - B\n3 - C\n4 - D\n5 - E\n6 - F\n7 - G\n8 - H\n9 - I\n10 - J";

        Files.writeString(Paths.get("key.txt"), keyFileContent);
        Files.writeString(Paths.get("answers.txt"), answersFileContent);

        // Тестирование
        int expectedScore = 20;
        int actualScore = processor.calculateTotalScore("key.txt", "answers.txt");

        assertEquals(expectedScore, actualScore);
    }

    @Test
    void testInvalidFileSize() throws IOException {
        // Подготовка данных
        String keyFileContent = "1 - A\n2 - B\n3 - C\n4 - D\n5 - E\n6 - F\n7 - G\n8 - H\n9 - I\n10 - J";
        String answersFileContent = "1 - A\n2 - B\n3 - C\n4 - D\n5 - E\n6 - F\n7 - G\n8 - H\n9 - I"; // One less answer

        Files.writeString(Paths.get("key.txt"), keyFileContent);
        Files.writeString(Paths.get("answers.txt"), answersFileContent);

        // Тестирование
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> processor.calculateTotalScore("key.txt", "answers.txt"));

        assertTrue(exception.getMessage().contains("Number of keys does not match number of answers"));
    }
}