package com.freemanatelier.freemanateliercustomstore;

import com.freemanatelier.freemanateliercustomstore.model.User;
import com.freemanatelier.freemanateliercustomstore.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@SpringBootApplication
public class FreemanAtelierCustomStoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(FreemanAtelierCustomStoreApplication.class, args);
    }

    CommandLineRunner init(UserRepository userRepo, BCryptPasswordEncoder encoder) {
        return args -> {
            if (userRepo.findByEmail("freemanatelier247@gmail.com") == null) {
                User admin = new User();
                admin.setEmail("okocharlesogbonnia@gmail.com");
                admin.setUsername("freeman");
                admin.setPassword(encoder.encode("freemanstore"));
                admin.setRoles(Set.of("ROLE_ADMIN"));
                userRepo.save(admin);
            }
        };
    }

}
