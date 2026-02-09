package by.step.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/api/v2")
@SessionAttributes({"user"})
public class GreetingController2 {

    @GetMapping("/hello")
    public String hello(Model model, HttpServletRequest request) {
        //        request.getSession().setAttribute(); sessionScope
        //        request.setAttribute(); requestScope
        //        request.getSession().getAttribute("user")
        model.addAttribute("user", new User(1L, "Ivan"));

        return "greeting/hello";
    }

    @GetMapping("/bye")
    public String bye(@SessionAttribute("user") User user) {
        //        request.getSession().getAttribute("user")

        return "greeting/bye";
    }

    @GetMapping("/hello/{id}")
    public String hello2(
            Model model,
            HttpServletRequest request,
            @RequestParam Integer age,
            @RequestHeader String accept,
            @CookieValue("JSESSIONID") String JSESSIONID,
            @PathVariable("id") Integer id) {
        String ageParamValue = request.getParameter("age");
        String acceptHeader = request.getHeader("accept");
        Cookie[] cookies = request.getCookies();

//        return "redirect:/api/v2/hello";
        return "redirect:https://onliner.by";

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
}