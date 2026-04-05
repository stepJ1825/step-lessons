package by.step.controller.advice;

import by.step.exception.ViewResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingRequestCookieException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@ControllerAdvice(assignableTypes = {by.step.controller.GreetingController.class})
public class GreetingControllerAdvice {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    /**
     * Обработка ошибок валидации сессии - отсутствие пользователя в сессии
     */
    @ExceptionHandler(IllegalStateException.class)
    public String handleSessionError(IllegalStateException ex, Model model) {
        log.warn("Session error in GreetingController: {}", ex.getMessage());

        model.addAttribute("errorTitle", "Ошибка сессии");
        model.addAttribute("errorMessage", "Ваша сессия истекла или повреждена. Пожалуйста, войдите заново.");
        model.addAttribute("errorTime", LocalDateTime.now().format(formatter));
        model.addAttribute("recommendation", "Перейдите на главную страницу и войдите в систему заново.");

        return "error/session-error";
    }

    /**
     * Обработка отсутствия обязательного параметра @RequestParam
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleMissingParameter(IllegalArgumentException ex,
                                         HttpServletRequest request,
                                         Model model) {
        log.warn("Missing or invalid parameter in GreetingController: {}", ex.getMessage());

        String parameterName = extractParameterName(ex.getMessage());

        model.addAttribute("errorTitle", "Ошибка в параметрах запроса");
        model.addAttribute("errorMessage", "Отсутствует или некорректен параметр: " + parameterName);
        model.addAttribute("errorTime", LocalDateTime.now().format(formatter));
        model.addAttribute("requestedUrl", request.getRequestURI());
        model.addAttribute("recommendation", "Проверьте правильность URL и попробуйте снова.");

        return "error/parameter-error";
    }

    /**
     * Обработка ошибки при преобразовании @PathVariable
     */
    @ExceptionHandler(NumberFormatException.class)
    public String handleNumberFormatException(NumberFormatException ex,
                                              HttpServletRequest request,
                                              Model model) {
        log.warn("Number format error in path variable: {}", ex.getMessage());

        model.addAttribute("errorTitle", "Неверный формат ID");
        model.addAttribute("errorMessage", "ID должен быть числом. Получено: " + extractInvalidValue(ex.getMessage()));
        model.addAttribute("errorTime", LocalDateTime.now().format(formatter));
        model.addAttribute("requestedUrl", request.getRequestURI());
        model.addAttribute("recommendation", "Используйте числовое значение для идентификатора.");

        return "error/number-format-error";
    }

    /**
     * Обработка ошибок при работе с куками
     */
    @ExceptionHandler(NullPointerException.class)
    public String handleCookieError(NullPointerException ex,
                                    HttpServletRequest request,
                                    Model model) {
        // Проверяем, связана ли ошибка с куками
        if (request.getCookies() == null || request.getCookies().length == 0) {
            log.warn("Cookie-related error in GreetingController");

            model.addAttribute("errorTitle", "Ошибка cookies");
            model.addAttribute("errorMessage", "Не удалось получить необходимые cookies для обработки запроса.");
            model.addAttribute("errorTime", LocalDateTime.now().format(formatter));
            model.addAttribute("requestedUrl", request.getRequestURI());
            model.addAttribute("recommendation", "Проверьте, что cookies включены в вашем браузере.");

            return "error/cookie-error";
        }

        // Общая ошибка NullPointer
        return handleGenericException(ex, request, model);
    }

    /**
     * Обработка ошибки при отсутствии атрибута куки
     */
    @ExceptionHandler(MissingRequestCookieException.class)
    public String handleMissingSessionAttribute(MissingRequestCookieException ex,
                                                Model model) {
        log.warn("Missing session attribute: {}", ex.getCookieName());

        model.addAttribute("errorTitle", "Отсутствуют данные cookie");
        model.addAttribute("errorMessage", "Не найден атрибут сессии: " + ex.getCookieName());
        model.addAttribute("errorTime", LocalDateTime.now().format(formatter));
        model.addAttribute("recommendation", "Пожалуйста, сначала перейдите на страницу /api/v1/hello для установки сессии.");

        return "error/session-attribute-error";
    }

    /**
     * Обработка ошибок при редиректах
     */
    @ExceptionHandler(IOException.class)
    public String handleIOError(IOException ex, Model model) {
        log.error("IO error in GreetingController: {}", ex.getMessage());

        model.addAttribute("errorTitle", "Ошибка ввода-вывода");
        model.addAttribute("errorMessage", "Произошла ошибка при обработке запроса: " + ex.getMessage());
        model.addAttribute("errorTime", LocalDateTime.now().format(formatter));
        model.addAttribute("recommendation", "Попробуйте обновить страницу или вернитесь позже.");

        return "error/io-error";
    }

    /**
     * Обработка всех остальных исключений в GreetingController
     */
    @ExceptionHandler(Exception.class)
    public String handleGenericException(Exception ex,
                                         HttpServletRequest request,
                                         Model model) {
        log.error("Unexpected error in GreetingController at {}: {}",
                request.getRequestURI(), ex.getMessage(), ex);

        // Получаем stack trace для отладки
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String stackTrace = sw.toString();

        model.addAttribute("errorTitle", "Непредвиденная ошибка");
        model.addAttribute("errorMessage", "Произошла ошибка при обработке вашего запроса.");
        model.addAttribute("errorDetails", ex.getMessage());
        model.addAttribute("errorTime", LocalDateTime.now().format(formatter));
        model.addAttribute("requestedUrl", request.getRequestURI());
        model.addAttribute("recommendation", "Пожалуйста, сообщите об этой ошибке администратору.");

        // Для разработки можно добавить stack trace (в production лучше убрать)
        model.addAttribute("stackTrace", stackTrace);

        return "error/generic-error";
    }

    /**
     * Вспомогательные методы
     */
    private String extractParameterName(String errorMessage) {
        if (errorMessage == null) return "неизвестный параметр";

        // Пытаемся извлечь имя параметра из сообщения
        if (errorMessage.contains("Required request parameter")) {
            int start = errorMessage.indexOf("'");
            int end = errorMessage.lastIndexOf("'");
            if (start != -1 && end != -1 && start < end) {
                return errorMessage.substring(start + 1, end);
            }
        }
        return errorMessage;
    }

    private String extractInvalidValue(String errorMessage) {
        if (errorMessage == null) return "неизвестное значение";

        // Извлекаем значение, которое не удалось преобразовать
        if (errorMessage.contains("For input string:")) {
            int start = errorMessage.indexOf("\"");
            int end = errorMessage.lastIndexOf("\"");
            if (start != -1 && end != -1 && start < end) {
                return errorMessage.substring(start + 1, end);
            }
        }
        return errorMessage;
    }

    /**
     * Добавление глобальных атрибутов для всех страниц ошибок
     */
    @ModelAttribute("appName")
    public String getAppName() {
        return "Book Management System";
    }

    @ModelAttribute("supportEmail")
    public String getSupportEmail() {
        return "support@step.by";
    }
}