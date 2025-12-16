package com.recetas.app.recetas_seguridad.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

    private JwtService jwtService;

    @BeforeEach
    void setUp() {
        jwtService = new JwtService();
        ReflectionTestUtils.setField(jwtService, "secret", "test-secret-key-for-unit-tests-minimum-256-bits-long");
        ReflectionTestUtils.setField(jwtService, "expiration", 86400000L);
    }

    @Test
    void generateToken_shouldCreateValidToken() {
        String token = jwtService.generateToken("testuser", "ROLE_USER");

        assertNotNull(token);
        assertFalse(token.isEmpty());
    }

    @Test
    void extractUsername_shouldReturnCorrectUsername() {
        String token = jwtService.generateToken("testuser", "ROLE_USER");

        String username = jwtService.extractUsername(token);

        assertEquals("testuser", username);
    }

    @Test
    void extractRole_shouldReturnCorrectRole() {
        String token = jwtService.generateToken("testuser", "ROLE_ADMIN");

        String role = jwtService.extractRole(token);

        assertEquals("ROLE_ADMIN", role);
    }

    @Test
    void isTokenValid_shouldReturnTrueForValidToken() {
        String token = jwtService.generateToken("testuser", "ROLE_USER");

        boolean isValid = jwtService.isTokenValid(token);

        assertTrue(isValid);
    }

    @Test
    void isTokenValid_shouldReturnFalseForInvalidToken() {
        String invalidToken = "invalid.token.here";

        boolean isValid = jwtService.isTokenValid(invalidToken);

        assertFalse(isValid);
    }

    @Test
    void isTokenValid_shouldReturnFalseForExpiredToken() {
        ReflectionTestUtils.setField(jwtService, "expiration", -1000L);
        String token = jwtService.generateToken("testuser", "ROLE_USER");

        boolean isValid = jwtService.isTokenValid(token);

        assertFalse(isValid);
    }
}
