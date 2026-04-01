package by.step;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
//@EnableFeignClients
public class FirstStepApplication {
    public static void main(String[] args) {
        SpringApplication.run(FirstStepApplication.class, args);
    }
}
