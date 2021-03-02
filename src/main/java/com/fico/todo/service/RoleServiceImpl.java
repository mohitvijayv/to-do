package com.fico.todo.service;

import com.fico.todo.model.Role;
import com.fico.todo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findByName(String name) {
        Role role  = roleRepository.findByRole(name);
        return role;
    }
}
