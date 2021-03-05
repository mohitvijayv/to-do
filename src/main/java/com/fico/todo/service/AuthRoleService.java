package com.fico.todo.service;

import com.fico.todo.model.AuthRole;

public interface AuthRoleService {
    AuthRole findByName(String name);
}
