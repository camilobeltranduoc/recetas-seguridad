package com.recetas.app.recetas_seguridad.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "recetas")
public class Receta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(length = 1000)
    private String descripcion;

    @Column(name = "tipo_cocina")
    private String tipoCocina;

    private String pais;

    private String dificultad;

    @ElementCollection
    @CollectionTable(name = "receta_ingredientes", joinColumns = @JoinColumn(name = "receta_id"))
    @Column(name = "ingrediente")
    private List<String> ingredientes;

    @ElementCollection
    @CollectionTable(name = "receta_pasos", joinColumns = @JoinColumn(name = "receta_id"))
    @Column(name = "paso", length = 500)
    private List<String> pasos;

    public Receta() {
    }

    public Receta(Long id, String nombre, String descripcion, String tipoCocina,
                  String pais, String dificultad, List<String> ingredientes, List<String> pasos) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipoCocina = tipoCocina;
        this.pais = pais;
        this.dificultad = dificultad;
        this.ingredientes = ingredientes;
        this.pasos = pasos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoCocina() {
        return tipoCocina;
    }

    public void setTipoCocina(String tipoCocina) {
        this.tipoCocina = tipoCocina;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public List<String> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<String> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public List<String> getPasos() {
        return pasos;
    }

    public void setPasos(List<String> pasos) {
        this.pasos = pasos;
    }
}
