package com.recetas.app.recetas_seguridad.config;

import com.recetas.app.recetas_seguridad.model.Receta;
import com.recetas.app.recetas_seguridad.model.Usuario;
import com.recetas.app.recetas_seguridad.repository.RecetaRepository;
import com.recetas.app.recetas_seguridad.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@Configuration
public class DataLoader {

    private static final String DEFAULT_ROLE = "ROLE_USER";

    @Bean
    CommandLineRunner initDatabase(RecetaRepository recetaRepository, UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (usuarioRepository.count() == 0) {
                String user1Password = System.getenv().getOrDefault("USER1_PASSWORD", "defaultPass123!");
                String user2Password = System.getenv().getOrDefault("USER2_PASSWORD", "defaultPass456!");
                String adminPassword = System.getenv().getOrDefault("ADMIN_PASSWORD", "adminSecure789!");

                usuarioRepository.save(new Usuario(null, "user1", passwordEncoder.encode(user1Password), DEFAULT_ROLE));
                usuarioRepository.save(new Usuario(null, "user2", passwordEncoder.encode(user2Password), DEFAULT_ROLE));
                usuarioRepository.save(new Usuario(null, "admin", passwordEncoder.encode(adminPassword), DEFAULT_ROLE));
            }

            if (recetaRepository.count() == 0) {
                recetaRepository.save(new Receta(
                        null,
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

                recetaRepository.save(new Receta(
                        null,
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

                recetaRepository.save(new Receta(
                        null,
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

                recetaRepository.save(new Receta(
                        null,
                        "Pasta Carbonara",
                        "Pasta italiana con huevo, queso y panceta",
                        "Italiana",
                        "Italia",
                        "Fácil",
                        Arrays.asList("400g pasta", "200g panceta", "4 huevos", "100g queso parmesano", "Pimienta negra"),
                        Arrays.asList(
                                "Cocer la pasta al dente",
                                "Dorar la panceta en una sartén",
                                "Batir los huevos con el queso rallado",
                                "Mezclar la pasta caliente con la panceta",
                                "Añadir la mezcla de huevo fuera del fuego",
                                "Servir con pimienta negra recién molida"
                        )
                ));

                recetaRepository.save(new Receta(
                        null,
                        "Ceviche Peruano",
                        "Pescado marinado en limón con cebolla y ají",
                        "Latina",
                        "Perú",
                        "Media",
                        Arrays.asList("500g pescado blanco", "10 limones", "1 cebolla morada", "Ají limo", "Cilantro", "Camote", "Choclo"),
                        Arrays.asList(
                                "Cortar el pescado en cubos pequeños",
                                "Exprimir los limones y marinar el pescado 5 minutos",
                                "Cortar la cebolla en juliana fina",
                                "Picar el ají y cilantro",
                                "Mezclar todos los ingredientes",
                                "Servir con camote y choclo hervidos"
                        )
                ));
            }
        };
    }
}
