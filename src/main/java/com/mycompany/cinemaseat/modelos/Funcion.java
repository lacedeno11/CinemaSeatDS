package com.mycompany.cinemaseat.modelos;

import java.util.List;

public class Funcion {
    private String sala;
    private String pelicula;
    private String horario;
    private String estado;
    private String idioma;
    private List<String> asientosOcupados;

    public Funcion(String sala, String pelicula, String horario, String estado, String idioma, List<String> asientosOcupados) {
        this.sala = sala;
        this.pelicula = pelicula;
        this.horario = horario;
        this.estado = estado;
        this.idioma = idioma;
        this.asientosOcupados = asientosOcupados;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public String getPelicula() {
        return pelicula;
    }

    public void setPelicula(String pelicula) {
        this.pelicula = pelicula;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public List<String> getAsientosOcupados() {
        return asientosOcupados;
    }

    public void setAsientosOcupados(List<String> asientosOcupados) {
        this.asientosOcupados = asientosOcupados;
    }
}