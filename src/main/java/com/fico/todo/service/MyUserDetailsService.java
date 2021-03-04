package com.fico.todo.service;
import com.fico.todo.model.auth.Role;
import com.fico.todo.model.auth.User;
import com.fico.todo.repository.auth.UserRepository;
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
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user==null){
            throw new UsernameNotFoundException("user 404");
        }
        return new UserPrincipal(user);
    }

    public Set getRoleSet(Principal principal){
        User user = userRepository.findByUsername(principal.getName());
        Set<String> roles = new HashSet<String>();
        Iterator value  = user.getRoles().iterator();
        while (value.hasNext()){
            roles.add(((Role) value.next()).getRole());
        }
        return roles;
    }

}
