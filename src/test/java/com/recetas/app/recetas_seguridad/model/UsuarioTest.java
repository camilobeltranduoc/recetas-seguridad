package com.recetas.app.recetas_seguridad.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    @Test
    void defaultConstructor_shouldCreateEmptyUsuario() {
        Usuario usuario = new Usuario();
        assertNotNull(usuario);
        assertNull(usuario.getId());
        assertNull(usuario.getUsername());
    }

    @Test
    void parameterizedConstructor_shouldSetAllFields() {
        Usuario usuario = new Usuario(1L, "testuser", "password123", "ROLE_USER");

        assertEquals(1L, usuario.getId());
        assertEquals("testuser", usuario.getUsername());
        assertEquals("password123", usuario.getPassword());
        assertEquals("ROLE_USER", usuario.getRole());
    }

    @Test
    void settersAndGetters_shouldWorkCorrectly() {
        Usuario usuario = new Usuario();

        usuario.setId(99L);
        assertEquals(99L, usuario.getId());

        usuario.setUsername("admin");
        assertEquals("admin", usuario.getUsername());

        usuario.setPassword("securePass");
        assertEquals("securePass", usuario.getPassword());

        usuario.setRole("ROLE_ADMIN");
        assertEquals("ROLE_ADMIN", usuario.getRole());
    }
}
