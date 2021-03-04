package com.fico.todo.service;

import com.fico.todo.model.AuthRole;

public interface RoleService {
    AuthRole findByName(String name);
}
