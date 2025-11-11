package com.recetas.app.recetas_seguridad.data;

import com.recetas.app.recetas_seguridad.model.Receta;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecetaData {

    private static final Map<Long, Receta> recetas = new HashMap<>();

    static {
        recetas.put(1L, new Receta(
            1L,
            "Paella Valenciana",
            "Plato tradicional español con arroz, mariscos y azafrán",
            "Mediterránea",
            "España",
            "Media",
            Arrays.asList("400g arroz", "500g mariscos variados", "1 pimiento rojo", "200g judías verdes", "Azafrán", "Caldo de pescado"),
            Arrays.asList(
                "Sofreír el pimiento y las judías verdes en aceite de oliva",
                "Añadir el arroz y tostar ligeramente",
                "Incorporar el caldo caliente con azafrán",
                "Agregar los mariscos y cocinar 18-20 minutos",
                "Dejar reposar 5 minutos antes de servir"
            )
        ));

        recetas.put(2L, new Receta(
            2L,
            "Tacos al Pastor",
            "Tacos mexicanos con carne de cerdo marinada y piña",
            "Mexicana",
            "México",
            "Fácil",
            Arrays.asList("500g carne de cerdo", "2 chiles guajillo", "1 piña", "Tortillas de maíz", "Cilantro", "Cebolla", "Limón"),
            Arrays.asList(
                "Marinar la carne con los chiles y especias por 2 horas",
                "Asar la carne en tiras finas",
                "Cortar la piña en cubos pequeños",
                "Calentar las tortillas",
                "Armar los tacos con carne, piña, cilantro y cebolla",
                "Servir con limón al lado"
            )
        ));

        recetas.put(3L, new Receta(
            3L,
            "Ramen Tradicional",
            "Sopa japonesa con fideos, caldo y diversos toppings",
            "Asiática",
            "Japón",
            "Difícil",
            Arrays.asList("200g fideos ramen", "1L caldo de huesos", "2 huevos", "200g carne de cerdo", "Alga nori", "Cebollín", "Salsa de soja"),
            Arrays.asList(
                "Preparar el caldo de huesos cociendo por 8-12 horas",
                "Marinar y cocinar la carne de cerdo",
                "Cocinar los huevos y partirlos por la mitad",
                "Cocer los fideos según instrucciones del paquete",
                "Calentar el caldo y añadir salsa de soja al gusto",
                "Servir los fideos en el caldo y decorar con carne, huevo, nori y cebollín"
            )
        ));
    }

    public static List<Receta> obtenerTodas() {
        return Arrays.asList(
            recetas.get(1L),
            recetas.get(2L),
            recetas.get(3L),
            new Receta(4L, "Pasta Carbonara", "Pasta italiana con huevo, queso y panceta", "Italiana", "Italia", "Fácil", null, null),
            new Receta(5L, "Ceviche Peruano", "Pescado marinado en limón con cebolla y ají", "Latina", "Perú", "Media", null, null)
        );
    }

    public static Receta obtenerPorId(Long id) {
        return recetas.get(id);
    }

    public static List<Receta> buscar(String nombre, String tipoCocina, String ingredientes, String pais, String dificultad) {
        return obtenerTodas().stream()
            .filter(r -> nombre == null || nombre.isEmpty() ||
                    r.getNombre().toLowerCase().contains(nombre.toLowerCase()))
            .filter(r -> tipoCocina == null || tipoCocina.isEmpty() ||
                    r.getTipoCocina().equalsIgnoreCase(tipoCocina))
            .filter(r -> pais == null || pais.isEmpty() ||
                    r.getPais().toLowerCase().contains(pais.toLowerCase()))
            .filter(r -> dificultad == null || dificultad.isEmpty() ||
                    r.getDificultad().equalsIgnoreCase(dificultad))
            .filter(r -> ingredientes == null || ingredientes.isEmpty() ||
                    (r.getIngredientes() != null &&
                     r.getIngredientes().stream().anyMatch(i ->
                        i.toLowerCase().contains(ingredientes.toLowerCase()))))
            .toList();
    }
}
