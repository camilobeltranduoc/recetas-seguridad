package com.recetas.app.recetas_seguridad.repository;

import com.recetas.app.recetas_seguridad.model.RecetaMultimedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecetaMultimediaRepository extends JpaRepository<RecetaMultimedia, Long> {
    List<RecetaMultimedia> findByRecetaId(Long recetaId);
}
