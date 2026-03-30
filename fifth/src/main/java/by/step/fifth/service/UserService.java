package by.step.fifth.service;

import by.step.fifth.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService implements UserDetailsService {

    // Хранилище пользователей в памяти
    private final List<User> users = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);
    private final PasswordEncoder passwordEncoder;

    public UserService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
        initTestUsers(); // Добавляем тестовых пользователей
    }

    private void initTestUsers() {
        // Тестовый пользователь с ролью USER
        registerUser("user", "user123", "user@example.com", Set.of("USER"));

        // Тестовый пользователь с ролью ADMIN
        registerUser("admin", "admin123", "admin@example.com", Set.of("ADMIN"));

        // Тестовый пользователь с обеими ролями
        registerUser("superuser", "super123", "super@example.com", Set.of("USER", "ADMIN"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return users.stream()
                    .filter(user -> user.getUsername().equals(username))
                    .findFirst()
                    .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

    public User registerUser(String username, String rawPassword, String email, Set<String> roles) {
        // Проверка на существование пользователя
        if (users.stream().anyMatch(u -> u.getUsername().equals(username))) {
            throw new RuntimeException("Username already exists: " + username);
        }

        if (users.stream().anyMatch(u -> u.getEmail().equals(email))) {
            throw new RuntimeException("Email already exists: " + email);
        }

        // Создаем нового пользователя
        User user = new User();
        user.setId(idCounter.getAndIncrement());
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(rawPassword)); // Хешируем пароль
        user.setEmail(email);
        user.setRoles(roles);
        user.setEnabled(true);

        users.add(user);

        System.out.println("User registered: " + username + " with roles: " + roles);
        return user;
    }

    public User findByUsername(String username) {
        return users.stream()
                    .filter(user -> user.getUsername().equals(username))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("User not found: " + username));
    }

    public User findById(Long id) {
        return users.stream()
                    .filter(user -> user.getId().equals(id))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    public void changePassword(String username, String oldPassword, String newPassword) {
        User user = findByUsername(username);

        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new RuntimeException("Old password is incorrect");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        System.out.println("Password changed for user: " + username);
    }

    public void deleteUser(String username) {
        users.removeIf(user -> user.getUsername().equals(username));
        System.out.println("User deleted: " + username);
    }

    // Метод для отображения всех пользователей (для демонстрации)
    public void printAllUsers() {
        System.out.println("\n=== All Users in Memory ===");
        users.forEach(user -> {
            System.out.println("ID: " + user.getId() +
                               ", Username: " + user.getUsername() +
                               ", Email: " + user.getEmail() +
                               ", Roles: " + user.getRoles() +
                               ", Password: " + user.getPassword().substring(0, 20) + "...");
        });
        System.out.println("Total users: " + users.size());
    }
}