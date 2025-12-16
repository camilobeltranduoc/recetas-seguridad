package com.recetas.app.recetas_seguridad.data;

import com.recetas.app.recetas_seguridad.model.Receta;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecetaDataTest {

    @Test
    void obtenerTodas_shouldReturn5Recetas() {
        List<Receta> recetas = RecetaData.obtenerTodas();
        assertNotNull(recetas);
        assertEquals(5, recetas.size());
    }

    @Test
    void obtenerTodas_shouldContainPaellaValenciana() {
        List<Receta> recetas = RecetaData.obtenerTodas();
        assertTrue(recetas.stream().anyMatch(r -> r.getNombre().equals("Paella Valenciana")));
    }

    @Test
    void obtenerPorId_shouldReturnReceta_whenIdExists() {
        Receta receta = RecetaData.obtenerPorId(1L);
        assertNotNull(receta);
        assertEquals("Paella Valenciana", receta.getNombre());
        assertEquals("España", receta.getPais());
    }

    @Test
    void obtenerPorId_shouldReturnNull_whenIdDoesNotExist() {
        Receta receta = RecetaData.obtenerPorId(999L);
        assertNull(receta);
    }

    @Test
    void buscar_shouldReturnAllRecetas_whenNoFilters() {
        List<Receta> result = RecetaData.buscar(null, null, null, null, null);
        assertEquals(5, result.size());
    }

    @Test
    void buscar_shouldFilterByNombre() {
        List<Receta> result = RecetaData.buscar("Paella", null, null, null, null);
        assertEquals(1, result.size());
        assertEquals("Paella Valenciana", result.get(0).getNombre());
    }

    @Test
    void buscar_shouldFilterByTipoCocina() {
        List<Receta> result = RecetaData.buscar(null, "Mexicana", null, null, null);
        assertEquals(1, result.size());
        assertEquals("Tacos al Pastor", result.get(0).getNombre());
    }

    @Test
    void buscar_shouldFilterByPais() {
        List<Receta> result = RecetaData.buscar(null, null, null, "Japón", null);
        assertEquals(1, result.size());
        assertEquals("Ramen Tradicional", result.get(0).getNombre());
    }

    @Test
    void buscar_shouldFilterByDificultad() {
        List<Receta> result = RecetaData.buscar(null, null, null, null, "Fácil");
        assertEquals(2, result.size());
    }

    @Test
    void buscar_shouldFilterByIngredientes() {
        List<Receta> result = RecetaData.buscar(null, null, "arroz", null, null);
        assertEquals(1, result.size());
        assertEquals("Paella Valenciana", result.get(0).getNombre());
    }

    @Test
    void buscar_shouldReturnEmptyList_whenNoMatches() {
        List<Receta> result = RecetaData.buscar("RecetaQueNoExiste", null, null, null, null);
        assertTrue(result.isEmpty());
    }

    @Test
    void buscar_shouldCombineMultipleFilters() {
        List<Receta> result = RecetaData.buscar(null, "Asiática", null, "Japón", "Difícil");
        assertEquals(1, result.size());
        assertEquals("Ramen Tradicional", result.get(0).getNombre());
    }

    @Test
    void buscar_shouldHandleEmptyStringFilters() {
        List<Receta> result = RecetaData.buscar("", "", "", "", "");
        assertEquals(5, result.size());
    }

    @Test
    void buscar_shouldBeCaseInsensitiveForNombre() {
        List<Receta> result = RecetaData.buscar("PAELLA", null, null, null, null);
        assertEquals(1, result.size());
    }

    @Test
    void buscar_shouldBeCaseInsensitiveForPais() {
        List<Receta> result = RecetaData.buscar(null, null, null, "españa", null);
        assertEquals(1, result.size());
    }

    @Test
    void buscar_shouldBeCaseInsensitiveForIngredientes() {
        List<Receta> result = RecetaData.buscar(null, null, "ARROZ", null, null);
        assertEquals(1, result.size());
    }
}
