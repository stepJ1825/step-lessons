package by.step.fifth.dto;

import by.step.fifth.model.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String email;
    private Role role; // "USER" или "ADMIN"

}
