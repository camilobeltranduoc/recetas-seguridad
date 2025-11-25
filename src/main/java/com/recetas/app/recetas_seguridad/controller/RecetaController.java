package com.recetas.app.recetas_seguridad.controller;

import com.recetas.app.recetas_seguridad.model.Receta;
import com.recetas.app.recetas_seguridad.service.RecetaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class RecetaController {

    private final RecetaService recetaService;

    public RecetaController(RecetaService recetaService) {
        this.recetaService = recetaService;
    }

    @GetMapping("/recetas/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        Optional<Receta> receta = recetaService.obtenerPorId(id);
        if (receta.isEmpty()) {
            return "redirect:/recetas";
        }
        model.addAttribute("receta", receta.get());
        return "detalle";
    }
}
