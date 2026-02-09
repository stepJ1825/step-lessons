package by.step.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/api/v1")
@SessionAttributes({"user"})
public class GreetingController2 {

    @ModelAttribute("roles")
    public List<Role> roles() {
        return Arrays.asList(Role.values());
    }

    @GetMapping("/hello")
    public String hello(
            Model model,
            HttpServletRequest request,
            @ModelAttribute("user") User user
    ) {
        //        request.getSession().setAttribute(); sessionScope
        //        request.setAttribute(); requestScope
        //        request.getSession().getAttribute("user")
        model.addAttribute("user", new User(1L, "Ivan"));

        return "greeting/hello";
    }

    @GetMapping("/bye")
    public String bye(@SessionAttribute("user") User user, Model model) {
        //        request.getSession().getAttribute("user")
        return "greeting/bye";
    }

    @GetMapping("/hello/{id}")
    public ModelAndView hello2(
            ModelAndView modelAndView, HttpServletRequest request,
            @RequestParam Integer age,
            @RequestHeader String accept,
            @CookieValue("JSESSIONID") String JSESSIONID,
            @PathVariable("id") Integer id
    ) {
        String ageParamValue = request.getParameter("age");
        String acceptHeader = request.getHeader("accept");
        Cookie[] cookies = request.getCookies();

        modelAndView.setViewName("greeting/hello");

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


    private enum Role {
        ADMIN, USER
    }
}