package com.recetas.app.recetas_seguridad.repository;

import com.recetas.app.recetas_seguridad.model.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecetaRepository extends JpaRepository<Receta, Long> {

    List<Receta> findByNombreContainingIgnoreCase(String nombre);

    List<Receta> findByTipoCocinaIgnoreCase(String tipoCocina);

    List<Receta> findByPaisContainingIgnoreCase(String pais);

    List<Receta> findByDificultadIgnoreCase(String dificultad);

    @Query("SELECT DISTINCT r FROM Receta r JOIN r.ingredientes i WHERE LOWER(i) LIKE LOWER(CONCAT('%', :ingrediente, '%'))")
    List<Receta> findByIngredientesContaining(@Param("ingrediente") String ingrediente);
}
