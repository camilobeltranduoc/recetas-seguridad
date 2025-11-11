package com.recetas.app.recetas_seguridad.controller;

import com.recetas.app.recetas_seguridad.data.RecetaData;
import com.recetas.app.recetas_seguridad.model.Receta;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RecetaController {

    @GetMapping("/recetas/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        Receta receta = RecetaData.obtenerPorId(id);
        if (receta == null) {
            return "redirect:/recetas";
        }
        model.addAttribute("receta", receta);
        return "detalle";
    }
}
