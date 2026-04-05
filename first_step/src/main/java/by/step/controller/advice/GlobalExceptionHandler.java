//package by.step.controller.advice;
//
//import by.step.dto.ErrorResponseDTO;
//import by.step.dto.ValidationErrorResponseDTO;
//import by.step.exception.DatabaseOperationException;
//import by.step.exception.DuplicateResourceException;
//import by.step.exception.InvalidRequestException;
//import by.step.exception.ResourceNotFoundException;
//import jakarta.servlet.http.HttpServletRequest;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.converter.HttpMessageNotReadableException;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.MissingServletRequestParameterException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
//
//import java.time.LocalDateTime;
//import java.util.HashMap;
//import java.util.Map;
//
//@Slf4j
//@RestControllerAdvice(basePackages = "by.step.controller") // Применяется ко всем контроллерам в пакете
//public class GlobalExceptionHandler {
//
//    // ========== Обработка 404 - Not Found ==========
//
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ErrorResponseDTO> handleResourceNotFound(
//            ResourceNotFoundException ex,
//            HttpServletRequest request) {
//
//        log.warn("Resource not found: {}", ex.getMessage());
//
//        ErrorResponseDTO error = new ErrorResponseDTO(
//                "RESOURCE_NOT_FOUND",
//                ex.getMessage(),
//                LocalDateTime.now(),
//                request.getRequestURI(),
//                HttpStatus.NOT_FOUND.value()
//        );
//
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
//    }
//
//    // Пример для BookRestController.findById - возвращает null
//    @ExceptionHandler(NullPointerException.class)
//    public ResponseEntity<ErrorResponseDTO> handleNullPointer(
//            NullPointerException ex,
//            HttpServletRequest request) {
//
//        log.error("Null pointer exception at: {}", request.getRequestURI(), ex);
//
//        // Проверяем, не является ли это случаем "книга не найдена"
//        if (ex.getMessage() != null && ex.getMessage().contains("book")) {
//            ErrorResponseDTO error = new ErrorResponseDTO(
//                    "BOOK_NOT_FOUND",
//                    "Requested book does not exist",
//                    LocalDateTime.now(),
//                    request.getRequestURI(),
//                    HttpStatus.NOT_FOUND.value()
//            );
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
//        }
//
//        ErrorResponseDTO error = new ErrorResponseDTO(
//                "INTERNAL_SERVER_ERROR",
//                "An unexpected error occurred",
//                LocalDateTime.now(),
//                request.getRequestURI(),
//                HttpStatus.INTERNAL_SERVER_ERROR.value()
//        );
//
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
//    }
//
//    // ========== Обработка 400 - Bad Request ==========
//
//    @ExceptionHandler(InvalidRequestException.class)
//    public ResponseEntity<ErrorResponseDTO> handleInvalidRequest(
//            InvalidRequestException ex,
//            HttpServletRequest request) {
//
//        log.warn("Invalid request: {}", ex.getMessage());
//
//        ErrorResponseDTO error = new ErrorResponseDTO(
//                "INVALID_REQUEST",
//                ex.getMessage(),
//                LocalDateTime.now(),
//                request.getRequestURI(),
//                HttpStatus.BAD_REQUEST.value()
//        );
//
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
//    }
//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<ValidationErrorResponseDTO> handleValidationExceptions(
//            MethodArgumentNotValidException ex,
//            HttpServletRequest request) {
//
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//
//        log.warn("Validation failed for request: {} - Errors: {}", request.getRequestURI(), errors);
//
//        ValidationErrorResponseDTO error = new ValidationErrorResponseDTO(
//                "VALIDATION_FAILED",
//                "Request validation failed",
//                LocalDateTime.now(),
//                request.getRequestURI(),
//                HttpStatus.BAD_REQUEST.value(),
//                errors
//        );
//
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
//    }
//
//    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
//    public ResponseEntity<ErrorResponseDTO> handleTypeMismatch(
//            MethodArgumentTypeMismatchException ex,
//            HttpServletRequest request) {
//
//        String message = String.format("Parameter '%s' with value '%s' cannot be converted to type '%s'",
//                ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());
//
//        log.warn("Type mismatch: {}", message);
//
//        ErrorResponseDTO error = new ErrorResponseDTO(
//                "TYPE_MISMATCH",
//                message,
//                LocalDateTime.now(),
//                request.getRequestURI(),
//                HttpStatus.BAD_REQUEST.value()
//        );
//
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
//    }
//
//    @ExceptionHandler(MissingServletRequestParameterException.class)
//    public ResponseEntity<ErrorResponseDTO> handleMissingParams(
//            MissingServletRequestParameterException ex,
//            HttpServletRequest request) {
//
//        String message = String.format("Required parameter '%s' is missing", ex.getParameterName());
//
//        log.warn("Missing parameter: {}", message);
//
//        ErrorResponseDTO error = new ErrorResponseDTO(
//                "MISSING_PARAMETER",
//                message,
//                LocalDateTime.now(),
//                request.getRequestURI(),
//                HttpStatus.BAD_REQUEST.value()
//        );
//
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
//    }
//
//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public ResponseEntity<ErrorResponseDTO> handleMessageNotReadable(
//            HttpMessageNotReadableException ex,
//            HttpServletRequest request) {
//
//        ErrorResponseDTO error = new ErrorResponseDTO(
//                "INVALID_REQUEST_BODY",
//                "Request body is malformed or contains invalid data",
//                LocalDateTime.now(),
//                request.getRequestURI(),
//                HttpStatus.BAD_REQUEST.value()
//        );
//
//        log.warn("Malformed request body: {}", ex.getMessage());
//
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
//    }
//
//    // ========== Обработка 409 - Conflict ==========
//
//    @ExceptionHandler(DuplicateResourceException.class)
//    public ResponseEntity<ErrorResponseDTO> handleDuplicateResource(
//            DuplicateResourceException ex,
//            HttpServletRequest request) {
//
//        log.warn("Duplicate resource: {}", ex.getMessage());
//
//        ErrorResponseDTO error = new ErrorResponseDTO(
//                "DUPLICATE_RESOURCE",
//                ex.getMessage(),
//                LocalDateTime.now(),
//                request.getRequestURI(),
//                HttpStatus.CONFLICT.value()
//        );
//
//        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
//    }
//
//    // ========== Обработка 500 - Internal Server Error ==========
//
//    @ExceptionHandler(DatabaseOperationException.class)
//    public ResponseEntity<ErrorResponseDTO> handleDatabaseOperation(
//            DatabaseOperationException ex,
//            HttpServletRequest request) {
//
//        log.error("Database operation failed: {}", ex.getMessage(), ex);
//
//        ErrorResponseDTO error = new ErrorResponseDTO(
//                "DATABASE_ERROR",
//                "Database operation failed: " + ex.getMessage(),
//                LocalDateTime.now(),
//                request.getRequestURI(),
//                HttpStatus.INTERNAL_SERVER_ERROR.value()
//        );
//
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
//    }
//
//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<ErrorResponseDTO> handleRuntimeException(
//            RuntimeException ex,
//            HttpServletRequest request) {
//
//        log.error("Runtime exception at {}: {}", request.getRequestURI(), ex.getMessage(), ex);
//
//        ErrorResponseDTO error = new ErrorResponseDTO(
//                "INTERNAL_SERVER_ERROR",
//                "An internal server error occurred. Please try again later.",
//                LocalDateTime.now(),
//                request.getRequestURI(),
//                HttpStatus.INTERNAL_SERVER_ERROR.value()
//        );
//
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
//    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErrorResponseDTO> handleGenericException(
//            Exception ex,
//            HttpServletRequest request) {
//
//        log.error("Unexpected error at {}: {}", request.getRequestURI(), ex.getMessage(), ex);
//
//        ErrorResponseDTO error = new ErrorResponseDTO(
//                "UNEXPECTED_ERROR",
//                "An unexpected error occurred",
//                LocalDateTime.now(),
//                request.getRequestURI(),
//                HttpStatus.INTERNAL_SERVER_ERROR.value()
//        );
//
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
//    }
//
//    // ========== Специфичные обработчики для ваших контроллеров ==========
//
//    // Обработка ошибки в UserController.getAllUsersFromMap()
//    @ExceptionHandler(IllegalStateException.class)
//    public ResponseEntity<ErrorResponseDTO> handleIllegalState(
//            IllegalStateException ex,
//            HttpServletRequest request) {
//
//        ErrorResponseDTO error = new ErrorResponseDTO(
//                "SERVICE_UNAVAILABLE",
//                "Service temporarily unavailable: " + ex.getMessage(),
//                LocalDateTime.now(),
//                request.getRequestURI(),
//                HttpStatus.SERVICE_UNAVAILABLE.value()
//        );
//
//        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(error);
//    }
//
//    // Обработка ошибки при id > 1000 в BookRestController
//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<ErrorResponseDTO> handleIllegalArgument(
//            IllegalArgumentException ex,
//            HttpServletRequest request) {
//
//        if (request.getRequestURI().matches(".*/books/\\d+") && ex.getMessage() == null) {
//            ErrorResponseDTO error = new ErrorResponseDTO(
//                    "INVALID_ID",
//                    "Book ID cannot exceed 1000",
//                    LocalDateTime.now(),
//                    request.getRequestURI(),
//                    HttpStatus.BAD_REQUEST.value()
//            );
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
//        }
//
//        ErrorResponseDTO error = new ErrorResponseDTO(
//                "ILLEGAL_ARGUMENT",
//                ex.getMessage() != null ? ex.getMessage() : "Invalid argument provided",
//                LocalDateTime.now(),
//                request.getRequestURI(),
//                HttpStatus.BAD_REQUEST.value()
//        );
//
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
//    }
//}