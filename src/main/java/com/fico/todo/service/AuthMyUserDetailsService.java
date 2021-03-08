package com.fico.todo.service;
import com.fico.todo.model.AuthRole;
import com.fico.todo.model.AuthUser;
import com.fico.todo.repository.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


@Service
public class AuthMyUserDetailsService implements UserDetailsService {

    @Autowired
    private AuthUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUser user = userRepository.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("user 404");
        }
        return new AuthUserPrincipal(user);
    }

    public Set getRoleSet(Principal principal){
        AuthUser user = userRepository.findByUsername(principal.getName());
        Set<String> roles = new HashSet<String>();
        Iterator value  = user.getRoles().iterator();
        while (value.hasNext()){
            roles.add(((AuthRole) value.next()).getRole());
        }
        return roles;
    }

}
