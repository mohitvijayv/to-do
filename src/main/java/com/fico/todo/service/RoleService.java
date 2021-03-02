package com.fico.todo.service;

import com.fico.todo.model.Role;

public interface RoleService {
    Role findByName(String name);
}
