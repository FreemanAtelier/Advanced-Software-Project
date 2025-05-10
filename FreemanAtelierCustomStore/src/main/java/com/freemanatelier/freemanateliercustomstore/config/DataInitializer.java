package com.freemanatelier.freemanateliercustomstore.config;

import com.freemanatelier.freemanateliercustomstore.model.User;
import com.freemanatelier.freemanateliercustomstore.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        String adminEmail = "okocharlesogbonnia@gmail.com";

        if (userRepository.findByEmail(adminEmail).isEmpty()) {
            User admin = new User();
            admin.setUsername("freeman");
            admin.setEmail(adminEmail);
            admin.setPassword(passwordEncoder.encode("freemanstore"));
            admin.setRoles(Collections.singleton("ROLE_ADMIN"));
            userRepository.save(admin);
        }
    }
}
