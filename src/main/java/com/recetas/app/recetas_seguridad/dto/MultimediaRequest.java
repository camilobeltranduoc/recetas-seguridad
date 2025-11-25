package com.recetas.app.recetas_seguridad.dto;

public class MultimediaRequest {
    private String tipo;
    private String url;

    public MultimediaRequest() {
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
}
