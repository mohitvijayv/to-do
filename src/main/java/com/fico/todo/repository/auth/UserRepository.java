package com.fico.todo.repository.auth;

import com.fico.todo.model.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findById(Optional<Long> userId);
}
