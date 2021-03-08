package com.fico.todo.service;

import com.fico.todo.model.AuthUser;
import com.fico.todo.repository.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public interface AuthUserService {

    AuthUser save(AuthUser user);

    AuthUser findById(Optional<Long> userId);

}
