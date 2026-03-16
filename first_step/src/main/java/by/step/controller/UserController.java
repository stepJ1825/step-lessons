package by.step.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final Map<Long, User> users = new HashMap<>();
    private long nextId = 1L;

    public UserController() {
        // Инициализация тестовых данных
        users.put(nextId, new User(nextId++, "John Doe", "john@example.com"));
        users.put(nextId, new User(nextId++, "Jane Smith", "jane@example.com"));
    }

    private List<User> getAllUsersFromMap(){
//        throw new RuntimeException();
        return new ArrayList<>(users.values());
    }

    @GetMapping // = @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> allUsersFromMap;
        try {
            allUsersFromMap = getAllUsersFromMap();
            return ResponseEntity.ok(allUsersFromMap);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

//    @GetMapping // = @RequestMapping(method = RequestMethod.GET)
//    public List<User> getAllUsers() {
//        return new ArrayList<>(users.values());
//    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(
            @PathVariable Long id,
            HttpServletRequest request,
            @RequestHeader("accept") String accept,  // = @RequestHeader String accept
            @CookieValue("JSESSIONID") String jsessionId
    ) {

        User user = users.get(id);
        if (user == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        user.setId(nextId++);
        users.put(user.getId(), user);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        if (!users.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        user.setId(id);
        users.put(id, user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (!users.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        users.remove(id);
        return ResponseEntity.noContent().build();
    }

    // DTO класс
    @Data
    @AllArgsConstructor
    private static class User {
        private Long id;
        private String name;
        private String email;
    }
}
