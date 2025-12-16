package com.recetas.app.recetas_seguridad.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ComentarioTest {

    @Test
    void defaultConstructor_shouldCreateComentarioWithFechaCreacion() {
        Comentario comentario = new Comentario();
        assertNotNull(comentario);
        assertNotNull(comentario.getFechaCreacion());
        assertNull(comentario.getId());
    }

    @Test
    void settersAndGetters_shouldWorkCorrectly() {
        Comentario comentario = new Comentario();
        Receta receta = new Receta();

        comentario.setId(1L);
        assertEquals(1L, comentario.getId());

        comentario.setReceta(receta);
        assertEquals(receta, comentario.getReceta());

        comentario.setUsuarioId(100L);
        assertEquals(100L, comentario.getUsuarioId());

        comentario.setUsername("testuser");
        assertEquals("testuser", comentario.getUsername());

        comentario.setTexto("Excelente receta");
        assertEquals("Excelente receta", comentario.getTexto());

        comentario.setValoracion(5);
        assertEquals(5, comentario.getValoracion());

        LocalDateTime fecha = LocalDateTime.now();
        comentario.setFechaCreacion(fecha);
        assertEquals(fecha, comentario.getFechaCreacion());
    }

    @Test
    void fechaCreacion_shouldBeSetAutomaticallyInConstructor() {
        LocalDateTime before = LocalDateTime.now().minusSeconds(1);
        Comentario comentario = new Comentario();
        LocalDateTime after = LocalDateTime.now().plusSeconds(1);

        assertNotNull(comentario.getFechaCreacion());
        assertTrue(comentario.getFechaCreacion().isAfter(before));
        assertTrue(comentario.getFechaCreacion().isBefore(after));
    }
}
