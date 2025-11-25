package com.recetas.app.recetas_seguridad.controller;

import com.recetas.app.recetas_seguridad.dto.ComentarioRequest;
import com.recetas.app.recetas_seguridad.dto.MultimediaRequest;
import com.recetas.app.recetas_seguridad.model.Comentario;
import com.recetas.app.recetas_seguridad.model.Receta;
import com.recetas.app.recetas_seguridad.model.RecetaMultimedia;
import com.recetas.app.recetas_seguridad.repository.ComentarioRepository;
import com.recetas.app.recetas_seguridad.repository.RecetaMultimediaRepository;
import com.recetas.app.recetas_seguridad.repository.UsuarioRepository;
import com.recetas.app.recetas_seguridad.service.RecetaService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/recetas/privado")
public class RecetaPrivadaController {

    private final RecetaService recetaService;
    private final RecetaMultimediaRepository multimediaRepository;
    private final ComentarioRepository comentarioRepository;
    private final UsuarioRepository usuarioRepository;

    public RecetaPrivadaController(RecetaService recetaService, RecetaMultimediaRepository multimediaRepository,
                                   ComentarioRepository comentarioRepository, UsuarioRepository usuarioRepository) {
        this.recetaService = recetaService;
        this.multimediaRepository = multimediaRepository;
        this.comentarioRepository = comentarioRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/{recetaId}/multimedia")
    public ResponseEntity<?> agregarMultimedia(@PathVariable Long recetaId,
                                               @RequestBody MultimediaRequest request,
                                               Authentication authentication) {
        Optional<Receta> receta = recetaService.obtenerPorId(recetaId);
        if (receta.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        String username = authentication.getName();
        Long usuarioId = usuarioRepository.findByUsername(username).map(u -> u.getId()).orElse(null);

        RecetaMultimedia multimedia = new RecetaMultimedia();
        multimedia.setReceta(receta.get());
        multimedia.setTipo(request.getTipo());
        multimedia.setUrl(request.getUrl());
        multimedia.setUsuarioId(usuarioId);

        multimediaRepository.save(multimedia);

        return ResponseEntity.ok(multimedia);
    }

    @GetMapping("/{recetaId}/multimedia")
    public ResponseEntity<List<RecetaMultimedia>> obtenerMultimedia(@PathVariable Long recetaId) {
        return ResponseEntity.ok(multimediaRepository.findByRecetaId(recetaId));
    }

    @PostMapping("/{recetaId}/comentarios")
    public ResponseEntity<?> agregarComentario(@PathVariable Long recetaId,
                                               @RequestBody ComentarioRequest request,
                                               Authentication authentication) {
        Optional<Receta> receta = recetaService.obtenerPorId(recetaId);
        if (receta.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        String username = authentication.getName();
        Long usuarioId = usuarioRepository.findByUsername(username).map(u -> u.getId()).orElse(null);

        Comentario comentario = new Comentario();
        comentario.setReceta(receta.get());
        comentario.setUsuarioId(usuarioId);
        comentario.setUsername(username);
        comentario.setTexto(request.getTexto());
        comentario.setValoracion(request.getValoracion());

        comentarioRepository.save(comentario);

        return ResponseEntity.ok(comentario);
    }

    @GetMapping("/{recetaId}/comentarios")
    public ResponseEntity<List<Comentario>> obtenerComentarios(@PathVariable Long recetaId) {
        return ResponseEntity.ok(comentarioRepository.findByRecetaIdOrderByFechaCreacionDesc(recetaId));
    }

    @PostMapping("/{recetaId}/compartir")
    public ResponseEntity<?> compartirReceta(@PathVariable Long recetaId,
                                             @RequestParam String plataforma,
                                             Authentication authentication) {
        Optional<Receta> receta = recetaService.obtenerPorId(recetaId);
        if (receta.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        String url = "http://localhost:8080/recetas/" + recetaId;
        String mensaje = "Mira esta receta: " + receta.get().getNombre();

        Map<String, String> enlaces = new HashMap<>();

        switch (plataforma.toLowerCase()) {
            case "facebook":
                enlaces.put("url", "https://www.facebook.com/sharer/sharer.php?u=" + url);
                break;
            case "twitter":
                enlaces.put("url", "https://twitter.com/intent/tweet?text=" + mensaje + "&url=" + url);
                break;
            case "whatsapp":
                enlaces.put("url", "https://wa.me/?text=" + mensaje + " " + url);
                break;
            case "email":
                enlaces.put("url", "mailto:?subject=" + mensaje + "&body=" + url);
                break;
            default:
                enlaces.put("url", url);
        }

        enlaces.put("mensaje", mensaje);
        enlaces.put("receta", receta.get().getNombre());

        return ResponseEntity.ok(enlaces);
    }
}
