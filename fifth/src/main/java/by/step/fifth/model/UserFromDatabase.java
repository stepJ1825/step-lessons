//package by.step.fifth.model;
//
//import jakarta.persistence.*;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.time.LocalDateTime;
//import java.util.Collection;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@Entity
//@Table(name = "users")
//@Data
//public class UserFromDatabase implements UserDetails {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(unique = true, nullable = false)
//    private String username;
//
//    @Column(nullable = false)
//    private String password;
//
//    @Column(unique = true, nullable = false)
//    private String email;
//
//    private boolean enabled = true;
//    private boolean accountNonExpired = true;
//    private boolean accountNonLocked = true;
//    private boolean credentialsNonExpired = true;
//
//    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
//    @Column(name = "role")
//    private Set<Role> roles;
//
//    private LocalDateTime createdAt;
//    private LocalDateTime updatedAt;
//
//    @PrePersist
//    protected void onCreate() {
//        createdAt = LocalDateTime.now();
//        updatedAt = LocalDateTime.now();
//    }
//
//    @PreUpdate
//    protected void onUpdate() {
//        updatedAt = LocalDateTime.now();
//    }
//
//    // Конструкторы
//    public UserFromDatabase() {
//    }
//
//    public UserFromDatabase(String username, String password, String email, Set<String> roles) {
//        this.username = username;
//        this.password = password;
//        this.email = email;
//        this.roles = roles;
//    }
//
//    // Реализация UserDetails
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return roles.stream()
//                    .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
//                    .collect(Collectors.toSet());
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return username;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return accountNonExpired;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return accountNonLocked;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return credentialsNonExpired;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return enabled;
//    }
//
//}