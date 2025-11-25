package com.recetas.app.recetas_seguridad.repository;

import com.recetas.app.recetas_seguridad.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    List<Comentario> findByRecetaIdOrderByFechaCreacionDesc(Long recetaId);
}
