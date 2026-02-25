//package by.step.config;
//
//import lombok.RequiredArgsConstructor;
//import org.flywaydb.core.Flyway;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.context.annotation.Profile;
//import org.springframework.stereotype.Component;
//
//@Component
//@Profile("spring-jdbc")
//@RequiredArgsConstructor
//public class FlywayCleaner implements ApplicationRunner {
//    private final Flyway flyway;
//
//    @Override
//    public void run(ApplicationArguments args) {
//        flyway.clean();   // Удаляет все таблицы
//        flyway.migrate(); // Применяет миграции заново
//    }
//}
