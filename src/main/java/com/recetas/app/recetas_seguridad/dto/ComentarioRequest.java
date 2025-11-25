package com.recetas.app.recetas_seguridad.dto;

public class ComentarioRequest {
    private String texto;
    private Integer valoracion;

    public ComentarioRequest() {
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Integer getValoracion() {
        return valoracion;
    }

    public void setValoracion(Integer valoracion) {
        this.valoracion = valoracion;
    }
}
