package com.fico.todo.service;

import com.fico.todo.model.auth.Role;

public interface RoleService {
    Role findByName(String name);
}
