package com.example.demo.user.service;

import com.example.demo.user.store.UserStore;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final UserStore userStore;

    public UserService(UserStore userStore) {
        this.userStore = userStore;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userStore.findByEmail(email)
                .map(u -> new org.springframework.security.core.userdetails.User(
                        u.email(), u.passwordHash(), List.of()))
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
    }
}
