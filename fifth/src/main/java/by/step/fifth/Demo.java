package by.step.fifth;

import by.step.fifth.model.Role;
import by.step.fifth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class Demo implements CommandLineRunner {
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        System.out.println("=== Spring Security Demo (In-Memory Storage) ===\n");

        // Демонстрация хеширования паролей
        System.out.println("1. Password Hashing Demo:");
        String testPassword = "myPassword123";
        String hash1 = passwordEncoder.encode(testPassword);
        String hash2 = passwordEncoder.encode(testPassword);

        System.out.println("   Original password: " + testPassword);
        System.out.println("   Hash 1: " + hash1);
        System.out.println("   Hash 2: " + hash2);
        System.out.println("   Hashes are different (due to salt): " + !hash1.equals(hash2));
        System.out.println("   Both verify correctly: " +
                           (passwordEncoder.matches(testPassword, hash1) && passwordEncoder.matches(
                                   testPassword,
                                   hash2
                           )));

        // Показываем всех пользователей в памяти
        System.out.println("\n2. Users in memory (initial):");
        userService.printAllUsers();

        // Демонстрация регистрации нового пользователя
        System.out.println("\n3. Registering new user...");
        userService.registerUser("testuser", "pass123", "test@example.com", Set.of(Role.USER));
        userService.printAllUsers();

        System.out.println("\n=== Available API Endpoints ===");
        System.out.println("POST   /api/auth/register    - Register new user");
        System.out.println("POST   /api/auth/login       - Login (returns session)");
        System.out.println("GET    /api/auth/me          - Get current user info");
        System.out.println("GET    /api/user/profile     - Get user profile (USER/ADMIN)");
        System.out.println("GET    /api/admin/users      - List all users (ADMIN)");
        System.out.println("DELETE /api/admin/users/{username} - Delete user (ADMIN)");

        System.out.println("\n=== Test Users ===");
        System.out.println("USER:  user / user123  (role: USER)");
        System.out.println("ADMIN: admin / admin123 (role: ADMIN)");
        System.out.println("SUPER: superuser / super123 (roles: USER, ADMIN)");

        System.out.println("\n=== How to Test ===");
        System.out.println("Use Basic Authentication with any of the test users");
        System.out.println("Example curl commands:");
        System.out.println("curl -u user:user123 http://localhost:9510/api/user/profile");
        System.out.println("curl -u admin:admin123 http://localhost:9510/api/admin/users");
    }

}
