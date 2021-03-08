package com.fico.todo.repository;

import com.fico.todo.model.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {
    AuthUser findByUsername(String username);

    AuthUser findById(Optional<Long> userId);
}
