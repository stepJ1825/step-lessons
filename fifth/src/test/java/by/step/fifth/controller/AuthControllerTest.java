package by.step.fifth.controller;

import by.step.fifth.dto.AuthRequest;
import by.step.fifth.dto.RegisterRequest;
import by.step.fifth.model.User;
import by.step.fifth.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.util.Set;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
@Import(TestSecurityConfig.class) // Импортируем тестовую конфигурацию
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private UserService userService;

    private User testUser;
    private RegisterRequest registerRequest;
    private AuthRequest authRequest;

    @BeforeEach
    void setUp() {
        testUser = new User();
        testUser.setId(1L);
        testUser.setUsername("testuser");
        testUser.setPassword("encodedPassword123");
        testUser.setEmail("test@example.com");
        testUser.setRoles(Set.of("USER"));
        testUser.setEnabled(true);

        registerRequest = new RegisterRequest();
        registerRequest.setUsername("newuser");
        registerRequest.setPassword("password123");
        registerRequest.setEmail("new@example.com");
        registerRequest.setRole("USER");

        authRequest = new AuthRequest();
        authRequest.setUsername("testuser");
        authRequest.setPassword("password123");
    }

    @Test
    @DisplayName("Should register new user successfully")
    void registerUser_Success() throws Exception {
        when(userService.registerUser(anyString(), anyString(), anyString(), anySet()))
                .thenReturn(testUser);

        mockMvc.perform(post("/api/auth/register")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(objectMapper.writeValueAsString(registerRequest))
                       .with(csrf())) // Добавляем CSRF токен
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.message", is("User registered successfully")))
               .andExpect(jsonPath("$.username", is("testuser")))
               .andExpect(jsonPath("$.email", is("test@example.com")))
               .andExpect(jsonPath("$.roles[0]", is("USER")));

        verify(userService, times(1)).registerUser(
                eq("newuser"), eq("password123"), eq("new@example.com"), eq(Set.of("USER"))
        );
    }

    @Test
    @DisplayName("Should return error when username already exists")
    void registerUser_UsernameExists() throws Exception {
        when(userService.registerUser(anyString(), anyString(), anyString(), anySet()))
                .thenThrow(new RuntimeException("Username already exists: newuser"));

        mockMvc.perform(post("/api/auth/register")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(objectMapper.writeValueAsString(registerRequest))
                       .with(csrf())) // Добавляем CSRF токен
               .andExpect(status().isBadRequest()) // Исправлено: ожидаем 400 вместо 403
               .andExpect(jsonPath("$.error", is("Username already exists: newuser")));

        verify(userService, times(1)).registerUser(anyString(), anyString(), anyString(), anySet());
    }

    @Test
    @DisplayName("Should return error when email already exists")
    void registerUser_EmailExists() throws Exception {
        when(userService.registerUser(anyString(), anyString(), anyString(), anySet()))
                .thenThrow(new RuntimeException("Email already exists: new@example.com"));

        mockMvc.perform(post("/api/auth/register")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(objectMapper.writeValueAsString(registerRequest))
                       .with(csrf())) // Добавляем CSRF токен
               .andExpect(status().isBadRequest()) // Исправлено: ожидаем 400 вместо 403
               .andExpect(jsonPath("$.error", is("Email already exists: new@example.com")));
    }

    @Test
    @DisplayName("Should login successfully with valid credentials")
    void login_Success() throws Exception {
        when(userService.findByUsername("testuser")).thenReturn(testUser);

        mockMvc.perform(post("/api/auth/login")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(objectMapper.writeValueAsString(authRequest)))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.message", is("Login successful")))
               .andExpect(jsonPath("$.username", is("testuser")))
               .andExpect(jsonPath("$.roles[0]", is("USER")))
               .andExpect(jsonPath("$.note", containsString("Basic Auth")));

        verify(userService, times(1)).findByUsername("testuser");
    }

    @Test
    @DisplayName("Should return unauthorized when user not found")
    void login_UserNotFound() throws Exception {
        when(userService.findByUsername("testuser"))
                .thenThrow(new RuntimeException("User not found: testuser"));

        mockMvc.perform(post("/api/auth/login")
                       .contentType(MediaType.APPLICATION_JSON)
                       .content(objectMapper.writeValueAsString(authRequest)))
               .andExpect(status().isUnauthorized())
               .andExpect(jsonPath("$.error", is("Invalid credentials")));
    }

    @Test
    @DisplayName("Should get current user info when authenticated")
    @WithMockUser(username = "testuser", roles = {"USER"})
    void getCurrentUser_Success() throws Exception {
        mockMvc.perform(get("/api/auth/me"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.username", is("testuser")))
               .andExpect(jsonPath("$.authenticated", is(true)))
               .andExpect(jsonPath("$.authorities", containsString("ROLE_USER")));
    }

    @Test
    @DisplayName("Should return 401 when not authenticated")
    void getCurrentUser_Unauthorized() throws Exception {
        mockMvc.perform(get("/api/auth/me"))
               .andExpect(status().isUnauthorized());
    }
}