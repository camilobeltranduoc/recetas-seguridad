package com.recetas.app.recetas_seguridad.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/recetas", "/buscar", "/login", "/css/**").permitAll()
                .requestMatchers("/recetas/**").authenticated()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/recetas", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/recetas")
                .permitAll()
            )
            .csrf(csrf -> csrf.ignoringRequestMatchers("/css/**"));

        http.headers(headers -> headers
            .frameOptions(frame -> frame.deny())
            .xssProtection(xss -> xss
                .headerValue(org.springframework.security.web.header.writers.XXssProtectionHeaderWriter.HeaderValue.ENABLED_MODE_BLOCK)
            )
            .contentTypeOptions(org.springframework.security.config.Customizer.withDefaults())
            .contentSecurityPolicy(csp -> csp
                .policyDirectives("default-src 'self'; " +
                                "script-src 'self'; " +
                                "style-src 'self'; " +
                                "img-src 'self' data:; " +
                                "font-src 'self'; " +
                                "object-src 'none'; " +
                                "base-uri 'self'; " +
                                "form-action 'self'; " +
                                "frame-ancestors 'none'; " +
                                "upgrade-insecure-requests")
            )
        );

        http.sessionManagement(session -> session
            .sessionFixation().migrateSession()
            .sessionCreationPolicy(org.springframework.security.config.http.SessionCreationPolicy.IF_REQUIRED)
        );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1 = User.builder()
            .username("user1")
            .password(passwordEncoder().encode("password1"))
            .roles("USER")
            .build();

        UserDetails user2 = User.builder()
            .username("user2")
            .password(passwordEncoder().encode("password2"))
            .roles("USER")
            .build();

        UserDetails admin = User.builder()
            .username("admin")
            .password(passwordEncoder().encode("admin123"))
            .roles("USER")
            .build();

        return new InMemoryUserDetailsManager(user1, user2, admin);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
