package com.recetas.app.recetas_seguridad.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MultimediaRequestTest {

    @Test
    void defaultConstructor_shouldCreateEmptyMultimediaRequest() {
        MultimediaRequest request = new MultimediaRequest();
        assertNotNull(request);
        assertNull(request.getTipo());
        assertNull(request.getUrl());
    }

    @Test
    void settersAndGetters_shouldWorkCorrectly() {
        MultimediaRequest request = new MultimediaRequest();

        request.setTipo("imagen");
        assertEquals("imagen", request.getTipo());

        request.setUrl("https://example.com/foto.jpg");
        assertEquals("https://example.com/foto.jpg", request.getUrl());
    }
}
