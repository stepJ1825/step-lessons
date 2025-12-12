package by.step;

import by.step.model.Book;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Locale;

public class DateTimeCheck {
    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        objectMapper.setLocale(Locale.ENGLISH);
        objectMapper.registerModule(new JSR310Module());

        TestClass build = TestClass.builder()
                .localDate(LocalDate.now())
                .localTime(LocalTime.now())
                .localDateTime(LocalDateTime.now())
                .build();

        TestClass o = objectMapper.readValue(
                objectMapper.writeValueAsString(build),
                new TypeReference<>() {
                }
        );
        System.out.println(o);
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    private static class TestClass{
        private LocalDate localDate;
        private LocalTime localTime;
        private LocalDateTime localDateTime;
    }
}
