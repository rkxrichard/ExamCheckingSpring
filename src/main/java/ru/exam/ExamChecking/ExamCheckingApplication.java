package ru.exam.ExamChecking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.exam.ExamChecking.config.ExamConfig;
import ru.exam.ExamChecking.services.InMemoryResultProcessor;
import ru.exam.ExamChecking.services.ResultProcessor;

import java.io.IOException;

@SpringBootApplication
public class ExamCheckingApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(ExamCheckingApplication.class, args);
		ApplicationContext context = new AnnotationConfigApplicationContext(ExamConfig.class);
		ResultProcessor resultProcessor = context.getBean(InMemoryResultProcessor.class);

		String keyFilePath = "path/to/key/file.txt";
		String answerFilePath = "path/to/answer/file.txt";

		int totalScore = resultProcessor.calculateTotalScore(keyFilePath, answerFilePath);
		System.out.println("Total Score: " + totalScore);
	}


}
