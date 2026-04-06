package by.step.controller.advice;

import by.step.controller.book.BookRestController;
import by.step.dto.ErrorResponseDTO;
import by.step.exception.InvalidRequestException;
import by.step.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice(basePackageClasses = {BookRestController.class, by.step.controller.UserController.class})
public class MyRestControllerAdvice {

    // Специфичная обработка для BookRestController.findById
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleBookNotFound(
            ResourceNotFoundException ex,
            HttpServletRequest request) {

        log.info("Book not found for request: {}", request.getRequestURI());

        ErrorResponseDTO error = new ErrorResponseDTO(
                "BOOK_NOT_FOUND",
                "MyRestControllerAdvice " + ex.getMessage(),
                LocalDateTime.now(),
                request.getRequestURI(),
                HttpStatus.NOT_FOUND.value()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidRequest(
            InvalidRequestException ex,
            HttpServletRequest request) {

        log.warn("Invalid request: {}", ex.getMessage());

        ErrorResponseDTO error = new ErrorResponseDTO(
                "INVALID_REQUEST",
                "MyRestControllerAdvice " + ex.getMessage(),
                LocalDateTime.now(),
                request.getRequestURI(),
                HttpStatus.BAD_REQUEST.value()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}