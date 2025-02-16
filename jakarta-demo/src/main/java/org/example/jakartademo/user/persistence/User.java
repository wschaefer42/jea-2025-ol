package org.example.jakartademo.user.persistence;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import static org.example.jakartademo.common.StringUtilsKt.toSlug;

@Entity
@Table(name = "users")
@Getter @Setter @NoArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(length = 100, unique = true, nullable = false)
    String username;
    String email;
    String countryCode = Country.getDefault().code();
    LocalDate modifiedAt = LocalDate.now();

    public User(String name) {
        this.username = name;
        this.email = toSlug(name) + "@example.com";
    }

    public User(String name, String countryCode) {
        this(name);
        this.countryCode = Country.getCountryByCode(countryCode).code();
    }

    public User(String name, String email, String countryCode) {
        this(name, countryCode);
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format("User{id=%d, username='%s' email=%s}", id, username, email);
    }
}
