package com.fico.todo.repository;

import com.fico.todo.model.AuthRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRoleRepository extends JpaRepository<AuthRole, Long> {
    AuthRole findByRole(String name);
}
