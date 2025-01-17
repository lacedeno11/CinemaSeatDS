package com.mycompany.cinemaseat.modelos;

import java.util.Map;

/**
 * Clase que representa una Sala en el sistema CinemaSeat.
 */
public class Sala {
    private String nombreSala;
    private int filas;
    private int columnas;
    private boolean disponible;
    private Map<String, Integer> distribucionAsientos;

    /**
     * Constructor de la clase Sala.
     *
     * @param nombreSala          Nombre de la sala.
     * @param filas               Número de filas en la sala.
     * @param columnas            Número de columnas en la sala.
     * @param disponible          Indica si la sala está disponible para nuevas funciones.
     * @param distribucionAsientos Mapa que define la distribución de tipos de asientos y su cantidad.
     */
    public Sala(String nombreSala, int filas, int columnas, boolean disponible, Map<String, Integer> distribucionAsientos) {
        this.nombreSala = nombreSala;
        this.filas = filas;
        this.columnas = columnas;
        this.disponible = disponible;
        this.distribucionAsientos = distribucionAsientos;
    }

    // Getters y Setters...

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

    /**
     * Sobrescribe el método toString para proporcionar una representación legible de la Sala.
     *
     * @return Cadena de texto que representa la Sala.
     */
    @Override
    public String toString() {
        return String.format("Sala{nombreSala='%s', filas=%d, columnas=%d, disponible=%s, distribucionAsientos=%s}",
                nombreSala, filas, columnas, disponible, distribucionAsientos.toString());
    }
}