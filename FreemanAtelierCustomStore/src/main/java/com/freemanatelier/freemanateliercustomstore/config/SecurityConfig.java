package com.freemanatelier.freemanateliercustomstore.config;

import com.freemanatelier.freemanateliercustomstore.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    //ADMIN SECURITY FILTER CHAIN
    @Bean
    @Order(1)
    public SecurityFilterChain adminSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/admin/**", "/adminpanel/**") // Handles admin endpoints
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/adminpanel/**").permitAll()
                        .anyRequest().hasRole("ADMIN")
                )
                .formLogin(login -> login
                        .loginPage("/adminpanel")
                        .loginProcessingUrl("/adminpanel")
                        .defaultSuccessUrl("/admin", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/admin/logout")
                        .logoutSuccessUrl("/adminpanel?logout")
                )
                .userDetailsService(userDetailsService)
                .csrf(csrf -> csrf.disable());

        return http.build();
    }

    // USER SECURITY FILTER CHAIN
    @Bean
    @Order(2)
    public SecurityFilterChain userSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/products/**", "/css/**", "/images/**", "/register", "/login", "/contact/**").permitAll()
                        .requestMatchers("/checkout/**", "/cart/**", "/home/**").authenticated()
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/login")
                        .defaultSuccessUrl("/home", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                )
                .userDetailsService(userDetailsService)
                .csrf(csrf -> csrf.disable());

        return http.build();
    }
}
