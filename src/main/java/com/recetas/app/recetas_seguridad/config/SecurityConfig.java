package com.recetas.app.recetas_seguridad.config;

import com.recetas.app.recetas_seguridad.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/recetas", "/buscar", "/login", "/css/**").permitAll()
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/api/recetas/privado/**").authenticated()
                .requestMatchers("/api/recetas", "/api/recetas/*", "/api/recetas/buscar").permitAll()
                .requestMatchers("/recetas/**").authenticated()
                .anyRequest().authenticated()
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .exceptionHandling(exceptions -> exceptions
                .defaultAuthenticationEntryPointFor(
                    (request, response, authException) -> response.sendError(401, "Unauthorized"),
                    request -> request.getRequestURI().startsWith("/api/")
                )
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/recetas", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/recetas")
                .permitAll()
            );

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

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
