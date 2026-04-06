package by.step.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/api/v1")
@SessionAttributes({"user"})
public class GreetingController {

    @GetMapping("/hello")
    public ModelAndView hello(ModelAndView modelAndView, HttpServletRequest request) {
        //        request.getSession().setAttribute(); sessionScope
        //        request.setAttribute(); requestScope
        //        request.getSession().getAttribute("user")
        modelAndView.setViewName("greeting/hello");
        modelAndView.addObject("user", new User(1L, "Ivan"));

        return modelAndView;
    }

    @GetMapping("/bye")
    public ModelAndView bye(@SessionAttribute("user") User user) {
        //        request.getSession().getAttribute("user")
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("greeting/bye");

        return modelAndView;
    }

    @GetMapping("/hello/{id}")
    public ModelAndView hello2(
            ModelAndView modelAndView,
            HttpServletRequest request,
            @RequestParam Integer age,
            @RequestHeader String accept,
            @CookieValue("JSESSIONID") String JSESSIONID,
            @PathVariable("id") Integer id) {
        String ageParamValue = request.getParameter("age");
        String acceptHeader = request.getHeader("accept");
        Cookie[] cookies = request.getCookies();

        modelAndView.setViewName("greeting/hello2");

        return modelAndView;
    }

    // DTO класс
    @Data
    @AllArgsConstructor
    private static class User {
        private Long id;
        private String name;
        private String email;

        public User(Long id, String name) {
            this.id = id;
            this.name = name;
        }
    }


    @GetMapping("/test-error/session")
    public String testSessionError() {
        throw new IllegalStateException("Session expired");
    }

    @GetMapping("/test-error/param")
    public String testParamError(@RequestParam String required) {
        // Если required отсутствует, выбросится исключение
        return "success";
    }

    @GetMapping("/test-error/number/{id}")
    public String testNumberError(@PathVariable Integer id) {
        return "success";
    }

}