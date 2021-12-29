package com.springMongoDb.Biblioteca.Collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "biblioteca")
public class Recurso {

    @Id
    private String id;
    private String name;
    private String tipoRecurso;
    private String areaTematica;
    private Boolean enPrestamo = false;
    private String fechaPrestamo;
    private String[] tiposRecurso = {"Libro", "Revista", "Material Audiovisual"};
    private String[] areasTematicas = {"Literatura", "Ciencia", "Arte", "Cine", "Historia"};

    public Recurso() {

    }

    public Recurso(String id, String name, String tipoRecurso, String areaTematica) {
        this.id = id;
        this.name = name;
        this.tipoRecurso = tipoRecurso;
        this.areaTematica = areaTematica;
        this.enPrestamo = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTipoRecurso() {
        return tipoRecurso;
    }

    public void setTipoRecurso(String tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }

    public String getAreaTematica() {
        return areaTematica;
    }

    public void setAreaTematica(String areaTematica) {
        this.areaTematica = areaTematica;
    }

    public Boolean getEnPrestamo() {
        return enPrestamo;
    }

    public void setEnPrestamo(Boolean enPrestamo) {
        this.enPrestamo = enPrestamo;
    }

    public String getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(String fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }
}
