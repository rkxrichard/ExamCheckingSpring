package ru.exam.ExamChecking.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.exam.ExamChecking.services.InMemoryResultProcessor;
import ru.exam.ExamChecking.services.ResultProcessor;

@Configuration
@PropertySource("classpath:application.properties")
public class ExamConfig {

    @Value("${points.for.group1}")
    private int pointForGroup1;

    @Value("${points.for.group2}")
    private int pointForGroup2;

    @Value("${points.for.group3}")
    private int pointForGroup3;

    public ResultProcessor resultProcess() {
        return new InMemoryResultProcessor(pointForGroup1, pointForGroup2, pointForGroup3);
    }

}
