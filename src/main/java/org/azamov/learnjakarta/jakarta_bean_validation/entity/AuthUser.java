package org.azamov.learnjakarta.jakarta_bean_validation.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class AuthUser extends Base {


    @Column(unique = true)
    private String token;
    @NotBlank
    @Column(unique = true)
    private String username;
    @Email
    @Column(unique = true)
    private String email;

    @Column(nullable = false)
    private String role = "USER";

    @Enumerated(EnumType.STRING)
    private Status status = Status.INACTIVE;


    @Column(nullable = false)
    private String password;

    @Builder(builderMethodName = "childMethodBuilder")
    public AuthUser(String username, String email, String role, Status status, String password) {
        this.username = username;
        this.email = email;
        this.role = role;
        this.status = status;
        this.password = password;
    }

    public enum Status {
        INACTIVE,
        ACTIVE,
        BLOCKED
    }
}
