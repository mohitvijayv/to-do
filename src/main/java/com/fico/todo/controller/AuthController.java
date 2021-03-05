package com.fico.todo.controller;

import com.fico.todo.model.AuthToken;
import com.fico.todo.model.AuthLoginUser;
import com.fico.todo.model.AuthUser;
import com.fico.todo.model.BaseApiResponse;
import com.fico.todo.security.TokenProvider;
import com.fico.todo.service.AuthUserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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


import static com.fico.todo.utilities.Constants.VERSION_V1;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AuthUserService userService;

    @Autowired
    TokenProvider tokenProvider;


    @PostMapping(path="/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Add a new user", response = AuthUser.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created new User"),
            @ApiResponse(code = 400, message = "Invalid post body or parameter")
    })
    public ResponseEntity register(@ApiParam(value = "Information of new User") @RequestBody AuthUser user){
        userService.save(user);
        return new ResponseEntity(user, HttpStatus.CREATED);
    }

    @PostMapping(value = "/authenticate")
    @ApiOperation(value = "Authenticate a user", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully authenticated User"),
            @ApiResponse(code = 400, message = "Invalid post body or parameter")
    })
    public ResponseEntity<?> generateToken(@ApiParam(value = "User Credentials") @RequestBody AuthLoginUser loginUser) throws AuthenticationException {

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
            BaseApiResponse br = new BaseApiResponse("F02", "USER NOT FOUND", VERSION_V1);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(br);
        }

    }






}
