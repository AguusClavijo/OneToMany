package com.OneToMany.OneToMany.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaLanzamiento;

    @ManyToOne
    @JoinColumn(name = "cantante_id")
    @JsonBackReference("cantante-album")
    private Cantante cantante;

    public Album() {
    }

    public Album(String titulo, LocalDate fechaLanzamiento, Cantante cantante) {
        this.titulo = titulo;
        this.fechaLanzamiento = fechaLanzamiento;
        this.cantante = cantante;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(LocalDate fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public Cantante getCantante() {
        return cantante;
    }

    public void setCantante(Cantante cantante) {
        this.cantante = cantante;
    }
}


