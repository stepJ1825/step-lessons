package by.step.config;

import by.step.config.condition.DBCondition;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Conditional(DBCondition.class)
@Configuration
public class DatabaseConfiguration {
}
