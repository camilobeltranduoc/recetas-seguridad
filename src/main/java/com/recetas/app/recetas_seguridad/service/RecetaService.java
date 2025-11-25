package com.recetas.app.recetas_seguridad.service;

import com.recetas.app.recetas_seguridad.model.Receta;
import com.recetas.app.recetas_seguridad.repository.RecetaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecetaService {

    private final RecetaRepository recetaRepository;

    public RecetaService(RecetaRepository recetaRepository) {
        this.recetaRepository = recetaRepository;
    }

    public List<Receta> obtenerTodas() {
        return recetaRepository.findAll();
    }

    public Optional<Receta> obtenerPorId(Long id) {
        return recetaRepository.findById(id);
    }

    public Receta guardar(Receta receta) {
        return recetaRepository.save(receta);
    }

    public void eliminar(Long id) {
        recetaRepository.deleteById(id);
    }

    public List<Receta> buscar(String nombre, String tipoCocina, String ingredientes, String pais, String dificultad) {
        List<Receta> resultado = recetaRepository.findAll();

        if (nombre != null && !nombre.isEmpty()) {
            resultado = resultado.stream()
                    .filter(r -> r.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                    .collect(Collectors.toList());
        }

        if (tipoCocina != null && !tipoCocina.isEmpty()) {
            resultado = resultado.stream()
                    .filter(r -> r.getTipoCocina().equalsIgnoreCase(tipoCocina))
                    .collect(Collectors.toList());
        }

        if (pais != null && !pais.isEmpty()) {
            resultado = resultado.stream()
                    .filter(r -> r.getPais().toLowerCase().contains(pais.toLowerCase()))
                    .collect(Collectors.toList());
        }

        if (dificultad != null && !dificultad.isEmpty()) {
            resultado = resultado.stream()
                    .filter(r -> r.getDificultad().equalsIgnoreCase(dificultad))
                    .collect(Collectors.toList());
        }

        if (ingredientes != null && !ingredientes.isEmpty()) {
            resultado = resultado.stream()
                    .filter(r -> r.getIngredientes() != null &&
                            r.getIngredientes().stream().anyMatch(i ->
                                    i.toLowerCase().contains(ingredientes.toLowerCase())))
                    .collect(Collectors.toList());
        }

        return resultado;
    }
}
