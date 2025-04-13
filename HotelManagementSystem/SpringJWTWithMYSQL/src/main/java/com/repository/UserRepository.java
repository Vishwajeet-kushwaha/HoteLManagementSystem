package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.Login;

@Repository
public interface UserRepository extends JpaRepository<Login, String> {
    Optional<Login> findByUsername(String username);
}