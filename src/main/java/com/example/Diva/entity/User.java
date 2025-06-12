package com.example.Diva.entity;

import com.example.Diva.security.entity.Token;
import com.example.Diva.utill.BaseEnums;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    private String phone;
    private String provider;
    private String providerId;

    @Enumerated(EnumType.STRING)
    private BaseEnums.Role role;

    private boolean enabled = true;

    @OneToMany(mappedBy = "user", cascade = ALL)
    private List<Address> addresses;

    // for customer
    @OneToMany(mappedBy = "customer")
    private List<Orders> orders;

    @OneToMany(mappedBy = "user")
    private List<Favorite> favorites;

    @OneToMany(mappedBy = "user")
    private List<Rating> ratings;

    @OneToMany(mappedBy = "user", cascade = ALL)
    private List<Token> tokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Return a collection of authorities for the user.
        // In your case, it's based on the 'role' enum.
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getPassword() {
        // Return the hashed password from the entity
        return password;
    }

    @Override
    public String getUsername() {
        // Return the username for authentication, which is typically the email
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        // Account expiration logic (e.g., based on a timestamp in the User entity)
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // Account locked status logic (e.g., based on a boolean in the User entity)
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // Password expiration logic
        return true;
    }

}