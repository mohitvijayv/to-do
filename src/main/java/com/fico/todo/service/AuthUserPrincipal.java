package com.fico.todo.service;

import com.fico.todo.model.AuthRole;
import com.fico.todo.model.AuthUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class AuthUserPrincipal implements UserDetails {

    private AuthUser user;

    public AuthUserPrincipal(AuthUser user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<AuthRole> roles = user.getRoles();
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        for(AuthRole role:roles){
            authorityList.add(new SimpleGrantedAuthority(role.getRole()));
        }
       return authorityList;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
