package com.recetas.app.recetas_seguridad.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecetaMultimediaTest {

    @Test
    void defaultConstructor_shouldCreateEmptyRecetaMultimedia() {
        RecetaMultimedia multimedia = new RecetaMultimedia();
        assertNotNull(multimedia);
        assertNull(multimedia.getId());
        assertNull(multimedia.getReceta());
    }

    @Test
    void settersAndGetters_shouldWorkCorrectly() {
        RecetaMultimedia multimedia = new RecetaMultimedia();
        Receta receta = new Receta();

        multimedia.setId(1L);
        assertEquals(1L, multimedia.getId());

        multimedia.setReceta(receta);
        assertEquals(receta, multimedia.getReceta());

        multimedia.setTipo("imagen");
        assertEquals("imagen", multimedia.getTipo());

        multimedia.setUrl("https://example.com/image.jpg");
        assertEquals("https://example.com/image.jpg", multimedia.getUrl());

        multimedia.setUsuarioId(50L);
        assertEquals(50L, multimedia.getUsuarioId());
    }
}
