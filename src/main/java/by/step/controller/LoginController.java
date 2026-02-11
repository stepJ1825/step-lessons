package by.step.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage() {
        return "greeting/login";
    }

    @PostMapping("/login")
    public String login(Model model, @ModelAttribute("login") LoginDto loginDto) {
        return "greeting/login";

        //        return "forward:/WEB-INF/views/greeting/login.html";
        //        return "redirect:https://google.com";
        //        return "redirect:/login";

    }

    private record LoginDto(String username, String password) {
    }
}
