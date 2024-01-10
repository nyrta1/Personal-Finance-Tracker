package com.nurik.userservice.service.authservice;

import com.nurik.userservice.models.AuthRequest;
import com.nurik.userservice.models.MessageResponse;
import com.nurik.userservice.models.Role;
import com.nurik.userservice.models.UserEntity;
import com.nurik.userservice.repository.RoleRepository;
import com.nurik.userservice.repository.UserRepository;
import com.nurik.userservice.security.jwt.JwtUtils;
import com.nurik.userservice.service.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserService userService;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager, JwtUtils jwtUtils, PasswordEncoder passwordEncoder, RoleRepository roleRepository, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userService = userService;
    }

    @Override
    public ResponseCookie authenticateUser(AuthRequest authUser) {
        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(authUser.getUsername(), authUser.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(authentication);

        return jwtCookie;
    }

    @Override
    public MessageResponse registerUser(AuthRequest authUser) {
        if (userService.existsByUsername(authUser.getUsername())) {
            return new MessageResponse("Error: Username is already taken!");
        }
        Role userRole = roleRepository.findByName(Role.RoleType.USER.name());
        UserEntity user = UserEntity.builder()
                .username(authUser.getUsername())
                .password(passwordEncoder.encode(authUser.getPassword()))
                .roles(List.of(userRole))
                .build();
        userService.registerTheUser(user);

        return new MessageResponse("User registered successfully!");
    }
}