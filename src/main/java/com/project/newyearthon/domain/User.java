package com.project.newyearthon.domain;

import com.project.newyearthon.domain.role.Guest;
import com.project.newyearthon.domain.role.Supplier;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USER_EMAIL", nullable = false, unique = true)
    private String email;

    @Column(name = "USER_PASSWORD", nullable = true)
    private String password;

    @Column(name = "USER_PHONE_NUMBER", nullable = true)
    private String phoneNumber;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, optional = true)
    private Guest guest;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, optional = true)
    private Supplier supplier;

    @Enumerated(EnumType.STRING)
    @Column(name = "USER_ROLE", nullable = false)
    private Role role;

    @Column(name = "USER_NAME", nullable = true)
    private String name;

    @Column(name = "USER_PROFILE_URL", nullable = true)
    private String profileUrl;
}
