package com.miempresa.miaplicacion.notifications.dto;

import com.miempresa.miaplicacion.notifications.model.Channel;

public class NotificationCreateRequestDTO {
    private String titulo;
    private String contenido;
    private Channel canal;
    private Long userId;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Channel getCanal() {
        return canal;
    }

    public void setCanal(Channel canal) {
        this.canal = canal;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
