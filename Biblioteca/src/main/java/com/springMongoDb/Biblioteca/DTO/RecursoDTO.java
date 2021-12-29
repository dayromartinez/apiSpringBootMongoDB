package com.springMongoDb.Biblioteca.DTO;


public class RecursoDTO {

    private String id;
    private String name;
    private String tipoRecurso;
    private String areaTematica;
    private Boolean enPrestamo = false;
    private String fechaPrestamo;

    public RecursoDTO() {
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
