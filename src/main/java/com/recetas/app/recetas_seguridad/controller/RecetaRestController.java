package com.recetas.app.recetas_seguridad.controller;

import com.recetas.app.recetas_seguridad.model.Receta;
import com.recetas.app.recetas_seguridad.service.RecetaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/recetas")
public class RecetaRestController {

    private final RecetaService recetaService;

    public RecetaRestController(RecetaService recetaService) {
        this.recetaService = recetaService;
    }

    @GetMapping
    public ResponseEntity<List<Receta>> obtenerTodas() {
        return ResponseEntity.ok(recetaService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receta> obtenerPorId(@PathVariable Long id) {
        Optional<Receta> receta = recetaService.obtenerPorId(id);
        return receta.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Receta>> buscar(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String tipoCocina,
            @RequestParam(required = false) String ingredientes,
            @RequestParam(required = false) String pais,
            @RequestParam(required = false) String dificultad) {
        return ResponseEntity.ok(recetaService.buscar(nombre, tipoCocina, ingredientes, pais, dificultad));
    }
}
