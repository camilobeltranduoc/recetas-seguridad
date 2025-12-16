package com.recetas.app.recetas_seguridad.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComentarioRequestTest {

    @Test
    void defaultConstructor_shouldCreateEmptyComentarioRequest() {
        ComentarioRequest request = new ComentarioRequest();
        assertNotNull(request);
        assertNull(request.getTexto());
        assertNull(request.getValoracion());
    }

    @Test
    void settersAndGetters_shouldWorkCorrectly() {
        ComentarioRequest request = new ComentarioRequest();

        request.setTexto("Excelente receta, muy fácil de seguir");
        assertEquals("Excelente receta, muy fácil de seguir", request.getTexto());

        request.setValoracion(5);
        assertEquals(5, request.getValoracion());
    }
}
