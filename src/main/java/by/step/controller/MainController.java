package by.step.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping()
    public String indexPage() {
        return "html-demo";
    }
}