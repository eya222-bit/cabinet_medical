package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // Permettre l'accès aux ressources statiques (CSS, JS)
                        .requestMatchers("/webjars/**", "/css/**", "/js/**").permitAll()
                        // Vos règles
                        .requestMatchers("/medecins/**").hasRole("ADMIN")
                        .requestMatchers("/patients/**").hasAnyRole("ADMIN", "SECRETAIRE")
                        .requestMatchers("/ordonnances/**").hasRole("MEDECIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        // On redirige vers /medecins/index après le login réussi
                        .defaultSuccessUrl("/medecins/index", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // --- AJOUTEZ CE BLOC POUR LE LOGIN ADMIN / ADMIN123 ---
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {

        // 1. ADMIN : accès complet (Médecins, Patients)
        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder.encode("admin123"))
                .roles("ADMIN")
                .build();

        // 2. MEDECIN : accès aux ordonnances
        UserDetails medecin = User.withUsername("medecin")
                .password(passwordEncoder.encode("medecin123")) // <-- Login mte3 el tbib
                .roles("MEDECIN")
                .build();

        // 3. SECRETAIRE : accès aux patients
        UserDetails secretaire = User.withUsername("secretaire")
                .password(passwordEncoder.encode("secretaire123"))
                .roles("SECRETAIRE")
                .build();

        return new InMemoryUserDetailsManager(admin, medecin, secretaire);
    }
}