package by.step.fifth.service;

import by.step.fifth.model.Role;
import by.step.fifth.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserServiceTest {

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
    }

    @Test
    @DisplayName("Should register new user successfully")
    void registerUser_Success() {
        User user = userService.registerUser("john", "password123", "john@example.com", Set.of(Role.USER));

        assertThat(user).isNotNull();
        assertThat(user.getUsername()).isEqualTo("john");
        assertThat(user.getEmail()).isEqualTo("john@example.com");
        assertThat(user.getRoles()).contains(Role.USER);
        assertThat(user.getPassword()).isNotEqualTo("password123"); // Should be encoded
        assertThat(user.isEnabled()).isTrue();
    }

    @Test
    @DisplayName("Should throw exception when registering duplicate username")
    void registerUser_DuplicateUsername() {
        userService.registerUser("john", "password123", "john@example.com", Set.of(Role.USER));

        assertThatThrownBy(() ->
                userService.registerUser("john", "password456", "john2@example.com", Set.of(Role.USER))
        ).isInstanceOf(RuntimeException.class)
         .hasMessageContaining("Username already exists");
    }

    @Test
    @DisplayName("Should throw exception when registering duplicate email")
    void registerUser_DuplicateEmail() {
        userService.registerUser("john", "password123", "john@example.com", Set.of(Role.USER));

        assertThatThrownBy(() ->
                userService.registerUser("john2", "password456", "john@example.com", Set.of(Role.USER))
        ).isInstanceOf(RuntimeException.class)
         .hasMessageContaining("Email already exists");
    }

    @Test
    @DisplayName("Should load user by username")
    void loadUserByUsername_Success() {
        userService.registerUser("john", "password123", "john@example.com", Set.of(Role.USER));

        UserDetails userDetails = userService.loadUserByUsername("john");

        assertThat(userDetails).isNotNull();
        assertThat(userDetails.getUsername()).isEqualTo("john");
        assertThat(userDetails.getAuthorities()).hasSize(1);
        assertThat(userDetails.getAuthorities().iterator().next().getAuthority())
                .isEqualTo("ROLE_USER");
    }

    @Test
    @DisplayName("Should throw exception when user not found")
    void loadUserByUsername_UserNotFound() {
        assertThatThrownBy(() ->
                userService.loadUserByUsername("nonexistent")
        ).isInstanceOf(UsernameNotFoundException.class)
         .hasMessageContaining("User not found");
    }

    @Test
    @DisplayName("Should find user by username")
    void findByUsername_Success() {
        userService.registerUser("john", "password123", "john@example.com", Set.of(Role.USER));

        User user = userService.findByUsername("john");

        assertThat(user).isNotNull();
        assertThat(user.getUsername()).isEqualTo("john");
    }

    @Test
    @DisplayName("Should throw exception when finding non-existent user")
    void findByUsername_NotFound() {
        assertThatThrownBy(() ->
                userService.findByUsername("nonexistent")
        ).isInstanceOf(RuntimeException.class)
         .hasMessageContaining("User not found");
    }

    @Test
    @DisplayName("Should change password successfully")
    void changePassword_Success() {
        userService.registerUser("john", "oldPassword", "john@example.com", Set.of(Role.USER));

        userService.changePassword("john", "oldPassword", "newPassword123");

        User user = userService.findByUsername("john");
        assertThat(user.getPassword()).isNotEqualTo("oldPassword");
        assertThat(user.getPassword()).isNotEqualTo("newPassword123"); // Should be encoded
    }

    @Test
    @DisplayName("Should throw exception when old password is incorrect")
    void changePassword_WrongOldPassword() {
        userService.registerUser("john", "correctPassword", "john@example.com", Set.of(Role.USER));

        assertThatThrownBy(() ->
                userService.changePassword("john", "wrongPassword", "newPassword")
        ).isInstanceOf(RuntimeException.class)
         .hasMessageContaining("Old password is incorrect");
    }

    @Test
    @DisplayName("Should delete user successfully")
    void deleteUser_Success() {
        userService.registerUser("john", "password", "john@example.com", Set.of(Role.USER));

        userService.deleteUser("john");

        assertThatThrownBy(() -> userService.findByUsername("john"))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("Should get all users")
    void getAllUsers() {
        userService.registerUser("user1", "pass1", "user1@example.com", Set.of(Role.USER));
        userService.registerUser("user2", "pass2", "user2@example.com", Set.of(Role.USER));
        userService.registerUser("admin2", "adminpass2", "admin2@example.com", Set.of(Role.ADMIN));

        var allUsers = userService.getAllUsers();

        assertThat(allUsers).hasSize(6); // 3 new + 3 default from initTestUsers()
        assertThat(allUsers).extracting(User::getUsername)
                            .contains("user1", "user2", "admin2", "user", "admin", "superuser");
    }
}