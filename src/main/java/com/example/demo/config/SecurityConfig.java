package com.example.demo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth

                                // accès public
                                .requestMatchers("/login", "/register").permitAll()

                                // rôles
                                .requestMatchers("/admin/**").hasAuthority("ADMIN")
                                .requestMatchers("/secretaire/**").hasAuthority("SECRETAIRE")
                                .requestMatchers("/medecin/**").hasAuthority("MEDECIN")
                                .requestMatchers("/patient/**").hasAuthority("PATIENT")

                                .anyRequest().authenticated()
                )
                .formLogin(form -> form
                                .loginPage("/login")
                                .defaultSuccessUrl("/home", true)
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    CommandLineRunner run(UserRepository userRepo, RoleRepository roleRepo, PasswordEncoder encoder) {
        return args -> {

            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(encoder.encode("1234"));
            userRepo.save(admin);
        };
    }
}