package com.Diatoz.java.Assesment.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Diatoz.java.Assesment.entity.User;
import com.Diatoz.java.Assesment.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired 
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User appUser = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
            appUser.getUsername(),
            appUser.getPassword(),
            Collections.singleton(new SimpleGrantedAuthority("ROLE_" + appUser.getRole()))
        );
    }
}
