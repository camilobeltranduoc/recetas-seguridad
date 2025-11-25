package com.recetas.app.recetas_seguridad.model;

import jakarta.persistence.*;

@Entity
@Table(name = "receta_multimedia")
public class RecetaMultimedia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "receta_id", nullable = false)
    private Receta receta;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false, length = 500)
    private String url;

    @Column(name = "usuario_id")
    private Long usuarioId;

    public RecetaMultimedia() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}
