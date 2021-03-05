package com.fico.todo.service;

import com.fico.todo.model.AuthRole;
import com.fico.todo.model.AuthUser;
import com.fico.todo.repository.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Service
public class AuthUserServiceImpl implements AuthUserService {

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private AuthRoleService roleService;

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

    @Override
    public AuthUser findById(Optional<Long> userId) {
        return userRepository.findById(userId);
    }


}
