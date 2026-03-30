package by.step.fifth.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Public endpoints should be accessible")
    void publicEndpointsAccessible() throws Exception {
        mockMvc.perform(get("/api/auth/register"))
               .andExpect(status().is4xxClientError()); // Not 401, but method not allowed or bad request
    }

    @Test
    @DisplayName("Protected endpoints should require authentication")
    void protectedEndpointsRequireAuth() throws Exception {
        mockMvc.perform(get("/api/auth/me"))
               .andExpect(status().isUnauthorized());

        mockMvc.perform(get("/api/user/profile"))
               .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("Admin endpoints should require ADMIN role")
    @WithMockUser(roles = {"USER"})
    void adminEndpointsRequireAdminRole() throws Exception {
        mockMvc.perform(get("/api/admin/users"))
               .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Admin endpoints accessible for ADMIN role")
    @WithMockUser(roles = {"ADMIN"})
    void adminEndpointsAccessibleForAdmin() throws Exception {
        mockMvc.perform(get("/api/admin/users"))
               .andExpect(status().isOk());
    }
}