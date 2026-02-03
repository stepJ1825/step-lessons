package by.step.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "by.step.controller")
public class WebConfig implements WebMvcConfigurer {
    // Spring 6 автоматически регистрирует MappingJackson2HttpMessageConverter,
    // если Jackson в classpath — ничего дополнительно делать не нужно.
}
