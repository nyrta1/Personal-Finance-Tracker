package com.nurik.userservice.models;

import com.nurik.userservice.annotations.UsernamePasswordEmailConstraint;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(unique = true)
    @UsernamePasswordEmailConstraint
    private String username;

    @Column(unique = true)
    @UsernamePasswordEmailConstraint
    private String password;

    @Column(unique = true)
    @UsernamePasswordEmailConstraint
    private String email;
}
