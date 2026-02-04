package by.step.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
//        "by.step.service",
//        "by.step.repository",
        "by.step"
})
public class RootConfig {
}
