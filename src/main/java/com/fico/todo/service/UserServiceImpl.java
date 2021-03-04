package com.fico.todo.service;

import com.fico.todo.model.AuthRole;
import com.fico.todo.model.AuthUser;
import com.fico.todo.repository.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class UserServiceImpl implements UserService {

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private RoleService roleService;

    @Autowired
    private AuthUserRepository userRepository;

    @Override
    public AuthUser save(AuthUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        AuthRole role = roleService.findByName("USER");
        Set<AuthRole> roleSet = new HashSet<>();
        roleSet.add(role);
        user.setRoles(roleSet);
        return userRepository.save(user);
    }
}
