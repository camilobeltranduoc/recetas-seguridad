package com.recetas.app.recetas_seguridad.service;

import com.recetas.app.recetas_seguridad.model.Receta;
import com.recetas.app.recetas_seguridad.repository.RecetaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecetaServiceTest {

    @Mock
    private RecetaRepository recetaRepository;

    @InjectMocks
    private RecetaService recetaService;

    private Receta receta1;
    private Receta receta2;

    @BeforeEach
    void setUp() {
        receta1 = new Receta();
        receta1.setId(1L);
        receta1.setNombre("Paella");
        receta1.setTipoCocina("Mediterránea");
        receta1.setPais("España");
        receta1.setDificultad("Media");
        receta1.setIngredientes(Arrays.asList("arroz", "mariscos"));

        receta2 = new Receta();
        receta2.setId(2L);
        receta2.setNombre("Tacos");
        receta2.setTipoCocina("Mexicana");
        receta2.setPais("México");
        receta2.setDificultad("Fácil");
        receta2.setIngredientes(Arrays.asList("tortilla", "carne"));
    }

    @Test
    void obtenerTodas_shouldReturnAllRecetas() {
        when(recetaRepository.findAll()).thenReturn(Arrays.asList(receta1, receta2));

        List<Receta> result = recetaService.obtenerTodas();

        assertEquals(2, result.size());
        verify(recetaRepository, times(1)).findAll();
    }

    @Test
    void obtenerPorId_shouldReturnReceta_whenExists() {
        when(recetaRepository.findById(1L)).thenReturn(Optional.of(receta1));

        Optional<Receta> result = recetaService.obtenerPorId(1L);

        assertTrue(result.isPresent());
        assertEquals("Paella", result.get().getNombre());
    }

    @Test
    void obtenerPorId_shouldReturnEmpty_whenNotExists() {
        when(recetaRepository.findById(999L)).thenReturn(Optional.empty());

        Optional<Receta> result = recetaService.obtenerPorId(999L);

        assertFalse(result.isPresent());
    }

    @Test
    void guardar_shouldSaveAndReturnReceta() {
        when(recetaRepository.save(receta1)).thenReturn(receta1);

        Receta result = recetaService.guardar(receta1);

        assertNotNull(result);
        assertEquals("Paella", result.getNombre());
        verify(recetaRepository, times(1)).save(receta1);
    }

    @Test
    void eliminar_shouldCallDeleteById() {
        doNothing().when(recetaRepository).deleteById(1L);

        recetaService.eliminar(1L);

        verify(recetaRepository, times(1)).deleteById(1L);
    }

    @Test
    void buscar_shouldFilterByNombre() {
        when(recetaRepository.findAll()).thenReturn(Arrays.asList(receta1, receta2));

        List<Receta> result = recetaService.buscar("Paella", null, null, null, null);

        assertEquals(1, result.size());
        assertEquals("Paella", result.get(0).getNombre());
    }

    @Test
    void buscar_shouldFilterByTipoCocina() {
        when(recetaRepository.findAll()).thenReturn(Arrays.asList(receta1, receta2));

        List<Receta> result = recetaService.buscar(null, "Mexicana", null, null, null);

        assertEquals(1, result.size());
        assertEquals("Mexicana", result.get(0).getTipoCocina());
    }

    @Test
    void buscar_shouldFilterByPais() {
        when(recetaRepository.findAll()).thenReturn(Arrays.asList(receta1, receta2));

        List<Receta> result = recetaService.buscar(null, null, null, "España", null);

        assertEquals(1, result.size());
        assertEquals("España", result.get(0).getPais());
    }

    @Test
    void buscar_shouldFilterByDificultad() {
        when(recetaRepository.findAll()).thenReturn(Arrays.asList(receta1, receta2));

        List<Receta> result = recetaService.buscar(null, null, null, null, "Fácil");

        assertEquals(1, result.size());
        assertEquals("Fácil", result.get(0).getDificultad());
    }

    @Test
    void buscar_shouldFilterByIngredientes() {
        when(recetaRepository.findAll()).thenReturn(Arrays.asList(receta1, receta2));

        List<Receta> result = recetaService.buscar(null, null, "arroz", null, null);

        assertEquals(1, result.size());
        assertTrue(result.get(0).getIngredientes().contains("arroz"));
    }

    @Test
    void buscar_shouldReturnAll_whenNoFilters() {
        when(recetaRepository.findAll()).thenReturn(Arrays.asList(receta1, receta2));

        List<Receta> result = recetaService.buscar(null, null, null, null, null);

        assertEquals(2, result.size());
    }
}
