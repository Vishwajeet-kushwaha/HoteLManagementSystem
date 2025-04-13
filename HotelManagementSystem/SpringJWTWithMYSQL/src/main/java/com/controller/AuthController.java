package com.controller;

import com.jwt.JwtUtility;
import com.model.Login;
import com.repository.UserRepository;
import com.request.LoginRequest;
import com.request.RegisterRequest;
import com.response.JSONResponse;
import com.service.UserDetailsImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/app")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtility jwtUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public JSONResponse authenticateUser(@RequestBody LoginRequest loginRequest) {
        System.out.println("🔐 Login attempt for username: " + loginRequest.getUsername());

        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("✅ Authentication successful for user: " + loginRequest.getUsername());

        String jwt = jwtUtils.generateToken(authentication);
        System.out.println("🪙 JWT generated: " + jwt);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        // Extract roles from UserDetails
        List<String> roles = userDetails.getAuthorities().stream()
            .map(role -> role.getAuthority())
            .collect(Collectors.toList());

        System.out.println("🎭 User roles: " + roles);

        // Return JSONResponse with roles
        return new JSONResponse(jwt, userDetails.getUsername(), roles);
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody RegisterRequest request) {
        System.out.println("📝 Registering user: " + request.getUsername());

        if (userRepository.existsById(request.getUsername())) {
            System.out.println("⚠️ Username already exists: " + request.getUsername());
            return "Username already exists.";
        }

        Login newUser = new Login();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setRole(request.getRole());

        userRepository.save(newUser);
        System.out.println("✅ User saved successfully: " + request.getUsername());

        return "User registered successfully.";
    }
}
