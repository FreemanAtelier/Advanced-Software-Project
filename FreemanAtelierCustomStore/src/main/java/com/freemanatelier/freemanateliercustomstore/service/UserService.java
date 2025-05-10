package com.freemanatelier.freemanateliercustomstore.service;

import com.freemanatelier.freemanateliercustomstore.model.User;
import com.freemanatelier.freemanateliercustomstore.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean register(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return false; // email already exists
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton("ROLE_USER"));
        userRepository.save(user);
        return true;
    }
}
