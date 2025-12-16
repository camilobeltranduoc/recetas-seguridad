package com.recetas.app.recetas_seguridad.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecetaTest {

    @Test
    void defaultConstructor_shouldCreateEmptyReceta() {
        Receta receta = new Receta();
        assertNotNull(receta);
        assertNull(receta.getId());
        assertNull(receta.getNombre());
    }

    @Test
    void parameterizedConstructor_shouldSetAllFields() {
        List<String> ingredientes = Arrays.asList("ingrediente1", "ingrediente2");
        List<String> pasos = Arrays.asList("paso1", "paso2");

        Receta receta = new Receta(1L, "Test Receta", "Descripción", "Italiana",
                "Italia", "Fácil", ingredientes, pasos);

        assertEquals(1L, receta.getId());
        assertEquals("Test Receta", receta.getNombre());
        assertEquals("Descripción", receta.getDescripcion());
        assertEquals("Italiana", receta.getTipoCocina());
        assertEquals("Italia", receta.getPais());
        assertEquals("Fácil", receta.getDificultad());
        assertEquals(ingredientes, receta.getIngredientes());
        assertEquals(pasos, receta.getPasos());
    }

    @Test
    void settersAndGetters_shouldWorkCorrectly() {
        Receta receta = new Receta();

        receta.setId(100L);
        assertEquals(100L, receta.getId());

        receta.setNombre("Paella");
        assertEquals("Paella", receta.getNombre());

        receta.setDescripcion("Una deliciosa paella");
        assertEquals("Una deliciosa paella", receta.getDescripcion());

        receta.setTipoCocina("Mediterránea");
        assertEquals("Mediterránea", receta.getTipoCocina());

        receta.setPais("España");
        assertEquals("España", receta.getPais());

        receta.setDificultad("Media");
        assertEquals("Media", receta.getDificultad());

        List<String> ingredientes = Arrays.asList("arroz", "mariscos");
        receta.setIngredientes(ingredientes);
        assertEquals(ingredientes, receta.getIngredientes());

        List<String> pasos = Arrays.asList("cocinar", "servir");
        receta.setPasos(pasos);
        assertEquals(pasos, receta.getPasos());
    }
}
