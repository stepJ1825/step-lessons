package by.step;

import by.step.methodtrace.EnableMethodTrace;
import by.step.methodtrace.MethodTraceLogger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableMethodTrace
//@EnableDiscoveryClient
//@EnableFeignClients
public class FirstStepApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext contex = SpringApplication.run(FirstStepApplication.class, args);
        MethodTraceLogger bean = contex.getBean(MethodTraceLogger.class);
    }
}
