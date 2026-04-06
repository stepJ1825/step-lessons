package by.step;

import by.step.methodtrace.EnableMethodTrace;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableMethodTrace
//@EnableDiscoveryClient
//@EnableFeignClients
public class FirstStepApplication {
    public static void main(String[] args) {
        SpringApplication.run(FirstStepApplication.class, args);
    }
}
