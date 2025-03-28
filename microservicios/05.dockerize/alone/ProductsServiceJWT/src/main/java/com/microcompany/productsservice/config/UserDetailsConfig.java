package com.microcompany.productsservice.config;

import com.microcompany.productsservice.model.ERole;
import com.microcompany.productsservice.persistence.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Configuration
public class UserDetailsConfig {

    @Autowired
    private UserRepository userRepo;
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {

            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                BCryptPasswordEncoder enc = new BCryptPasswordEncoder();

                List<com.microcompany.productsservice.model.User> users = List.of(
                        new com.microcompany.productsservice.model.User(1, "user@email.com", enc.encode("upass"), ERole.USER),
                        new com.microcompany.productsservice.model.User(1, "admin@email.com", enc.encode("apass"), ERole.ADMIN),
                        new com.microcompany.productsservice.model.User(1, "gestor@email.com", enc.encode("gpass"), ERole.GESTOR),
                        new com.microcompany.productsservice.model.User(2, "cliente@email.com", enc.encode("cpass"), ERole.CLIENTE)
                );

                return users.stream()
                        .filter(u -> u.getEmail().equals(username))
                        .findFirst()
                        .orElseThrow(() -> new UsernameNotFoundException("User with username - " + username + " not found"));
            }

        };
    }

}
