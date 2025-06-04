package com.Diatoz.java.Assesment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Diatoz.java.Assesment.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
