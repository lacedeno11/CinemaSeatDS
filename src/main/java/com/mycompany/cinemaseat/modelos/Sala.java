package com.mycompany.cinemaseat.modelos;


import java.util.Map;

public class Sala {
    private String nombreSala;
    private int filas;
    private int columnas;
    private boolean disponible; // Nuevo campo
    private Map<String, Integer> distribucionAsientos;

    public Sala(String nombreSala, int filas, int columnas, boolean disponible, Map<String, Integer> distribucionAsientos) {
        this.nombreSala = nombreSala;
        this.filas = filas;
        this.columnas = columnas;
        this.disponible = disponible;
        this.distribucionAsientos = distribucionAsientos;
    }

    public String getNombreSala() {
        return nombreSala;
    }

    public void setNombreSala(String nombreSala) {
        this.nombreSala = nombreSala;
    }

    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Map<String, Integer> getDistribucionAsientos() {
        return distribucionAsientos;
    }

    public void setDistribucionAsientos(Map<String, Integer> distribucionAsientos) {
        this.distribucionAsientos = distribucionAsientos;
    }
}