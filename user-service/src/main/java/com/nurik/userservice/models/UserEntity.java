package com.nurik.userservice.models;

import com.nurik.userservice.annotations.UsernamePasswordEmailConstraint;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    @UsernamePasswordEmailConstraint
    private String username;

    @UsernamePasswordEmailConstraint
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL, CascadeType.REMOVE})
    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private List<Role> roles = new ArrayList<>();
}
