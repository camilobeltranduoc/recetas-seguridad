package com.recetas.app.recetas_seguridad.controller;

import com.recetas.app.recetas_seguridad.data.RecetaData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping({"/", "/recetas"})
    public String inicio(Model model) {
        model.addAttribute("recetas", RecetaData.obtenerTodas());
        return "index";
    }

    @GetMapping("/buscar")
    public String buscar(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String tipoCocina,
            @RequestParam(required = false) String ingredientes,
            @RequestParam(required = false) String pais,
            @RequestParam(required = false) String dificultad,
            Model model) {

        model.addAttribute("resultados", RecetaData.buscar(nombre, tipoCocina, ingredientes, pais, dificultad));
        model.addAttribute("nombre", nombre);
        model.addAttribute("tipoCocina", tipoCocina);
        model.addAttribute("ingredientes", ingredientes);
        model.addAttribute("pais", pais);
        model.addAttribute("dificultad", dificultad);

        return "buscar";
    }
}
