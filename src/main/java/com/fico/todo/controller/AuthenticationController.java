package com.fico.todo.controller;

import com.fico.todo.model.*;
import com.fico.todo.security.TokenProvider;
import com.fico.todo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.fico.todo.utilities.Constants.VERSION_V1;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    TokenProvider tokenProvider;

    @PostMapping(path="/register", consumes = {"application/json"})
    public User register(@RequestBody User user){
        userService.save(user);
        return user;
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> generateToken(@RequestBody LoginUser loginUser) throws AuthenticationException {

        try{
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginUser.getUsername(),
                            loginUser.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            final String token = tokenProvider.generateToken(authentication);
            return ResponseEntity.ok(new AuthToken(token));
        } catch (Exception e){
            System.out.println("ERROR WHILE AUTH VALIDATE ###########");
            //TaskApiResponse resp = new TaskApiResponse("F02", "USER NOT FOUND", VERSION_V1);
            BaseApiResponse br = new BaseApiResponse("F02", "USER NOT FOUND", VERSION_V1);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(br);
        }

    }






}
