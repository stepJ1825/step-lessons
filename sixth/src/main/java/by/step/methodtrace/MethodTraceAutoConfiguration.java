package by.step.methodtrace;


import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ConditionalOnClass(MethodTraceAspect.class)
@EnableConfigurationProperties(MethodTraceProperties.class)
@ConditionalOnProperty(name = "method.trace.enabled", havingValue = "true", matchIfMissing = true)
public class MethodTraceAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(MethodTraceAutoConfiguration.class);

    @Bean
    @ConditionalOnMissingBean
    public MethodTraceLogger methodTraceLogger() {
        log.info("MethodTraceLogger bean created"); // Отладка
        return new MethodTraceLogger();
    }

    @Bean
    @ConditionalOnMissingBean
    public MethodTraceAspect methodTraceAspect() {
        log.info("MethodTraceAspect bean created"); // Отладка
        return new MethodTraceAspect();
    }
}