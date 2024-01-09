package com.nurik.userservice.repository;

import com.nurik.userservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository
        extends JpaRepository<User, Long> {
}
