//package by.step.controller.advice;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.NoHandlerFoundException;
//
//@Slf4j
//@ControllerAdvice
//public class ViewExceptionHandler {
//
//    /**
//     * Обработка 404 ошибки для View
//     */
//    @ExceptionHandler(NoHandlerFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public String handleNotFound(NoHandlerFoundException ex, Model model) {
//        log.warn("Page not found: {}", ex.getRequestURL());
//
//        model.addAttribute("errorTitle", "Страница не найдена");
//        model.addAttribute("errorMessage", "Запрашиваемая страница не существует.");
//        model.addAttribute("requestedUrl", ex.getRequestURL());
//        model.addAttribute("errorTime", java.time.LocalDateTime.now());
//        model.addAttribute("recommendation", "Проверьте правильность URL или вернитесь на главную страницу.");
//
//        return "error/404";
//    }
//
//    /**
//     * Обработка ошибки 500
//     */
//    @ExceptionHandler({RuntimeException.class, Exception.class})
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ModelAndView handleInternalServerError(Exception ex) {
//        log.error("Internal server error: {}", ex.getMessage(), ex);
//
//        ModelAndView mav = new ModelAndView("error/500");
//        mav.addObject("errorTitle", "Внутренняя ошибка сервера");
//        mav.addObject("errorMessage", "Сервер не смог обработать запрос.");
//        mav.addObject("errorDetails", ex.getMessage());
//        mav.addObject("errorTime", java.time.LocalDateTime.now());
//
//        return mav;
//    }
//}