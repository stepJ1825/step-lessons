package by.step.fifth.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {
    @GetMapping("/hello")
//    @PreAuthorize("isAuthenticated()")
    @PreAuthorize("hasRole('ADMIN')")
    public String hello(){
        return "Hello world";
    }
}
