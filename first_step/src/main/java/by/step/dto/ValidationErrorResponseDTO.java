package by.step.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationErrorResponseDTO {
    private String errorCode;
    private String message;
    private LocalDateTime timestamp;
    private String path;
    private int status;
    private Map<String, String> validationErrors;
}
