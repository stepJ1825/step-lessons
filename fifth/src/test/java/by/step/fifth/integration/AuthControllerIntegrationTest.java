package by.step.fifth.integration;

import by.step.fifth.dto.AuthRequest;
import by.step.fifth.dto.RegisterRequest;
import by.step.fifth.model.User;
import by.step.fifth.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private RegisterRequest registerRequest;
    private AuthRequest authRequest;

    @BeforeEach
    void setUp() {
        // Очищаем пользователей перед каждым тестом
        userService.getAllUsers().forEach(user ->
                userService.deleteUser(user.getUsername())
        );

        // Создаем тестового пользователя
        userService.registerUser("existinguser", "pass123", "existing@example.com", Set.of(Role.USER));

        registerRequest = new RegisterRequest();
        registerRequest.setUsername("newuser");
        registerRequest.setPassword("password123");
        registerRequest.setEmail("new@example.com");
        registerRequest.setRole(Role.USER);

        authRequest = new AuthRequest();
        authRequest.setUsername("existinguser");
        authRequest.setPassword("pass123");
    }

    @Test
    @DisplayName("Integration: Full registration and login flow")
    void fullRegistrationAndLoginFlow() throws Exception {
        // 1. Регистрация нового пользователя
        mockMvc.perform(post("/api/auth/register")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(objectMapper.writeValueAsString(registerRequest)))
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.message", is("User registered successfully")))
               .andExpect(jsonPath("$.username", is("newuser")));

        // 2. Логин с новым пользователем
        AuthRequest loginRequest = new AuthRequest();
        loginRequest.setUsername("newuser");
        loginRequest.setPassword("password123");

        mockMvc.perform(post("/api/auth/login")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(objectMapper.writeValueAsString(loginRequest)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.username", is("newuser")));

        // 3. Проверка, что пользователь существует в системе
        User user = userService.findByUsername("newuser");
        assert user != null;
        assert user.getEmail().equals("new@example.com");
    }

    @Test
    @DisplayName("Integration: Duplicate registration should fail")
    void duplicateRegistrationFails() throws Exception {
        // Первая регистрация
        mockMvc.perform(post("/api/auth/register")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(objectMapper.writeValueAsString(registerRequest)))
               .andExpect(status().isCreated());

        // Попытка зарегистрировать того же пользователя
        mockMvc.perform(post("/api/auth/register")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(objectMapper.writeValueAsString(registerRequest)))
               .andExpect(status().isBadRequest())
               .andExpect(jsonPath("$.error", containsString("Username already exists")));
    }

    @Test
    @DisplayName("Integration: Admin can list all users")
    void adminCanListAllUsers() throws Exception {
        // Создаем несколько пользователей
        userService.registerUser("user1", "pass1", "user1@example.com", Set.of(Role.USER));
        userService.registerUser("user2", "pass2", "user2@example.com", Set.of(Role.USER));
        userService.registerUser("admin1", "adminpass", "admin@example.com", Set.of(Role.ADMIN));

        // Админ получает список всех пользователей
        mockMvc.perform(get("/api/admin/users")
                       .with(org.springframework.security.test.web.servlet.request
                               .SecurityMockMvcRequestPostProcessors.httpBasic("admin1", "adminpass")))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(4)))) // including existinguser
               .andExpect(jsonPath("$[*].username", hasItems("user1", "user2", "admin1")));
    }
}
