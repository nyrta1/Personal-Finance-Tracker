package com.nurik.userservice.repository;

import com.nurik.userservice.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository
        extends JpaRepository<Role, Long> {
    Role findByName(String roleName);
}
