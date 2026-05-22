package com.example.demo.user.store;

import com.example.demo.user.model.User;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserStore {

    private final Map<String, User> byEmail = new ConcurrentHashMap<>();

    public boolean existsByEmail(String email) {
        return byEmail.containsKey(email.toLowerCase());
    }

    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(byEmail.get(email.toLowerCase()));
    }

    public void save(User user) {
        byEmail.put(user.email().toLowerCase(), user);
    }
}
