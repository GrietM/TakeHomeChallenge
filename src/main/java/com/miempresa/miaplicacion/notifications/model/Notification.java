package com.miempresa.miaplicacion.notifications.model;

import jakarta.persistence.*;

@Entity
@Table(name = "notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String contenido;
    @Enumerated(EnumType.STRING)
    @Column(name = "canal")
    private Channel canal;

    public Notification() {}

    public Notification (String title, String content, Channel channel ){
        this.titulo=title;
        this.contenido=content;
        this.canal=channel;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
