package com.recetas.app.recetas_seguridad.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginRequestTest {

    @Test
    void defaultConstructor_shouldCreateEmptyLoginRequest() {
        LoginRequest request = new LoginRequest();
        assertNotNull(request);
        assertNull(request.getUsername());
        assertNull(request.getPassword());
    }

    @Test
    void parameterizedConstructor_shouldSetAllFields() {
        LoginRequest request = new LoginRequest("testuser", "password123");

        assertEquals("testuser", request.getUsername());
        assertEquals("password123", request.getPassword());
    }

    @Test
    void settersAndGetters_shouldWorkCorrectly() {
        LoginRequest request = new LoginRequest();

        request.setUsername("admin");
        assertEquals("admin", request.getUsername());

        request.setPassword("securePass");
        assertEquals("securePass", request.getPassword());
    }
}
