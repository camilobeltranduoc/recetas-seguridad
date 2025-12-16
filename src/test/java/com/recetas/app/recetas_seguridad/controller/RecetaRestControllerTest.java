package com.recetas.app.recetas_seguridad.controller;

import com.recetas.app.recetas_seguridad.model.Receta;
import com.recetas.app.recetas_seguridad.service.RecetaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecetaRestControllerTest {

    @Mock
    private RecetaService recetaService;

    @InjectMocks
    private RecetaRestController controller;

    private Receta receta1;
    private Receta receta2;

    @BeforeEach
    void setUp() {
        receta1 = new Receta();
        receta1.setId(1L);
        receta1.setNombre("Paella");

        receta2 = new Receta();
        receta2.setId(2L);
        receta2.setNombre("Tacos");
    }

    @Test
    void obtenerTodas_shouldReturnAllRecetas() {
        when(recetaService.obtenerTodas()).thenReturn(Arrays.asList(receta1, receta2));

        ResponseEntity<List<Receta>> response = controller.obtenerTodas();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }

    @Test
    void obtenerPorId_shouldReturnReceta_whenExists() {
        when(recetaService.obtenerPorId(1L)).thenReturn(Optional.of(receta1));

        ResponseEntity<Receta> response = controller.obtenerPorId(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Paella", response.getBody().getNombre());
    }

    @Test
    void obtenerPorId_shouldReturn404_whenNotExists() {
        when(recetaService.obtenerPorId(999L)).thenReturn(Optional.empty());

        ResponseEntity<Receta> response = controller.obtenerPorId(999L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void buscar_shouldReturnFilteredRecetas() {
        when(recetaService.buscar("Paella", null, null, null, null))
                .thenReturn(Arrays.asList(receta1));

        ResponseEntity<List<Receta>> response = controller.buscar("Paella", null, null, null, null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals("Paella", response.getBody().get(0).getNombre());
    }
}
