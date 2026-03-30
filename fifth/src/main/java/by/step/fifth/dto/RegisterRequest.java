package by.step.fifth.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
    private String role; // "USER" или "ADMIN"

}
