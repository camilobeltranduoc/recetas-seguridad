package com.recetas.app.recetas_seguridad.controller;

import com.recetas.app.recetas_seguridad.dto.ComentarioRequest;
import com.recetas.app.recetas_seguridad.dto.MultimediaRequest;
import com.recetas.app.recetas_seguridad.model.Comentario;
import com.recetas.app.recetas_seguridad.model.Receta;
import com.recetas.app.recetas_seguridad.model.RecetaMultimedia;
import com.recetas.app.recetas_seguridad.model.Usuario;
import com.recetas.app.recetas_seguridad.repository.ComentarioRepository;
import com.recetas.app.recetas_seguridad.repository.RecetaMultimediaRepository;
import com.recetas.app.recetas_seguridad.repository.UsuarioRepository;
import com.recetas.app.recetas_seguridad.service.RecetaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecetaPrivadaControllerTest {

    @Mock
    private RecetaService recetaService;

    @Mock
    private RecetaMultimediaRepository multimediaRepository;

    @Mock
    private ComentarioRepository comentarioRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private RecetaPrivadaController controller;

    private Receta receta;
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        receta = new Receta();
        receta.setId(1L);
        receta.setNombre("Paella");

        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setUsername("testuser");
    }

    @Test
    void agregarMultimedia_shouldAddMultimedia_whenRecetaExists() {
        MultimediaRequest request = new MultimediaRequest();
        request.setTipo("foto");
        request.setUrl("http://example.com/photo.jpg");

        when(recetaService.obtenerPorId(1L)).thenReturn(Optional.of(receta));
        when(authentication.getName()).thenReturn("testuser");
        when(usuarioRepository.findByUsername("testuser")).thenReturn(Optional.of(usuario));
        when(multimediaRepository.save(any(RecetaMultimedia.class))).thenAnswer(i -> i.getArgument(0));

        ResponseEntity<?> response = controller.agregarMultimedia(1L, request, authentication);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(multimediaRepository, times(1)).save(any(RecetaMultimedia.class));
    }

    @Test
    void agregarMultimedia_shouldReturn404_whenRecetaNotFound() {
        MultimediaRequest request = new MultimediaRequest();
        when(recetaService.obtenerPorId(999L)).thenReturn(Optional.empty());

        ResponseEntity<?> response = controller.agregarMultimedia(999L, request, authentication);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void obtenerMultimedia_shouldReturnMultimediaList() {
        RecetaMultimedia multimedia = new RecetaMultimedia();
        when(multimediaRepository.findByRecetaId(1L)).thenReturn(Arrays.asList(multimedia));

        ResponseEntity<List<RecetaMultimedia>> response = controller.obtenerMultimedia(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void agregarComentario_shouldAddComentario_whenRecetaExists() {
        ComentarioRequest request = new ComentarioRequest();
        request.setTexto("Excelente receta");
        request.setValoracion(5);

        when(recetaService.obtenerPorId(1L)).thenReturn(Optional.of(receta));
        when(authentication.getName()).thenReturn("testuser");
        when(usuarioRepository.findByUsername("testuser")).thenReturn(Optional.of(usuario));
        when(comentarioRepository.save(any(Comentario.class))).thenAnswer(i -> i.getArgument(0));

        ResponseEntity<?> response = controller.agregarComentario(1L, request, authentication);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(comentarioRepository, times(1)).save(any(Comentario.class));
    }

    @Test
    void agregarComentario_shouldReturn404_whenRecetaNotFound() {
        ComentarioRequest request = new ComentarioRequest();
        when(recetaService.obtenerPorId(999L)).thenReturn(Optional.empty());

        ResponseEntity<?> response = controller.agregarComentario(999L, request, authentication);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void obtenerComentarios_shouldReturnComentariosList() {
        Comentario comentario = new Comentario();
        when(comentarioRepository.findByRecetaIdOrderByFechaCreacionDesc(1L))
                .thenReturn(Arrays.asList(comentario));

        ResponseEntity<List<Comentario>> response = controller.obtenerComentarios(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void compartirReceta_shouldGenerateFacebookUrl() {
        when(recetaService.obtenerPorId(1L)).thenReturn(Optional.of(receta));

        ResponseEntity<?> response = controller.compartirReceta(1L, "facebook", authentication);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Map<String, String> body = (Map<String, String>) response.getBody();
        assertNotNull(body);
        assertTrue(body.get("url").contains("facebook.com"));
    }

    @Test
    void compartirReceta_shouldGenerateTwitterUrl() {
        when(recetaService.obtenerPorId(1L)).thenReturn(Optional.of(receta));

        ResponseEntity<?> response = controller.compartirReceta(1L, "twitter", authentication);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Map<String, String> body = (Map<String, String>) response.getBody();
        assertNotNull(body);
        assertTrue(body.get("url").contains("twitter.com"));
    }

    @Test
    void compartirReceta_shouldGenerateWhatsappUrl() {
        when(recetaService.obtenerPorId(1L)).thenReturn(Optional.of(receta));

        ResponseEntity<?> response = controller.compartirReceta(1L, "whatsapp", authentication);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Map<String, String> body = (Map<String, String>) response.getBody();
        assertNotNull(body);
        assertTrue(body.get("url").contains("wa.me"));
    }

    @Test
    void compartirReceta_shouldGenerateEmailUrl() {
        when(recetaService.obtenerPorId(1L)).thenReturn(Optional.of(receta));

        ResponseEntity<?> response = controller.compartirReceta(1L, "email", authentication);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Map<String, String> body = (Map<String, String>) response.getBody();
        assertNotNull(body);
        assertTrue(body.get("url").contains("mailto:"));
    }

    @Test
    void compartirReceta_shouldReturn404_whenRecetaNotFound() {
        when(recetaService.obtenerPorId(999L)).thenReturn(Optional.empty());

        ResponseEntity<?> response = controller.compartirReceta(999L, "facebook", authentication);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
