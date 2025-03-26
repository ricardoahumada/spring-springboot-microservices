package com.microcompany.productsservice.config;

import com.microcompany.productsservice.model.ERole;
import com.microcompany.productsservice.model.User;
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

            BCryptPasswordEncoder enc = new BCryptPasswordEncoder();

            List<User> users = List.of(
              new User(1, "user@mail.com",enc.encode("my_pass"), ERole.USER),
              new User(2, "admin@mail.com",enc.encode("my_pass"), ERole.ADMIN),
              new User(3, "client@mail.com",enc.encode("my_pass"), ERole.CLIENTE),
              new User(4, "gestor@mail.com",enc.encode("my_pass"), ERole.GESTOR)
            );

            /*@Override
            public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
                return userRepo.findByEmail(email)
                        .orElseThrow(
                                () -> new UsernameNotFoundException(
                                        "User " + email + " not found"
                                )
                        );
            }*/

            @Override
            public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
                return users.stream()
                        .filter(u-> u.getEmail().equals(email))
                        .findFirst()
                        .orElseThrow(()->new UsernameNotFoundException("Usuario con email: "+email+" no encontrado"));
            }

        };
    }

}
