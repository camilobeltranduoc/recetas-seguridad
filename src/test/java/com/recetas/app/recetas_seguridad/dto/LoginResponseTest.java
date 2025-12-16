package com.recetas.app.recetas_seguridad.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginResponseTest {

    @Test
    void defaultConstructor_shouldCreateEmptyLoginResponse() {
        LoginResponse response = new LoginResponse();
        assertNotNull(response);
        assertNull(response.getToken());
        assertNull(response.getUsername());
        assertNull(response.getRole());
    }

    @Test
    void parameterizedConstructor_shouldSetAllFields() {
        LoginResponse response = new LoginResponse("jwt-token", "testuser", "ROLE_USER");

        assertEquals("jwt-token", response.getToken());
        assertEquals("testuser", response.getUsername());
        assertEquals("ROLE_USER", response.getRole());
    }

    @Test
    void settersAndGetters_shouldWorkCorrectly() {
        LoginResponse response = new LoginResponse();

        response.setToken("new-token");
        assertEquals("new-token", response.getToken());

        response.setUsername("admin");
        assertEquals("admin", response.getUsername());

        response.setRole("ROLE_ADMIN");
        assertEquals("ROLE_ADMIN", response.getRole());
    }
}
