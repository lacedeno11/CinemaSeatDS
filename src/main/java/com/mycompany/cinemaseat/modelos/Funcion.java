package com.mycompany.cinemaseat.modelos;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Funcion {
    private String id;
    private String sala;
    private String pelicula;
    private String horario;
    private String estado;
    private String idioma;
    private Set<String> asientosOcupados;

    public Funcion(String sala, String pelicula, String horario, String estado, String idioma, Set<String> asientosOcupados) {
        this.id = UUID.randomUUID().toString();
        this.sala = sala;
        this.pelicula = pelicula;
        this.horario = horario;
        this.estado = estado;
        this.idioma = idioma;
        this.asientosOcupados = asientosOcupados != null ? asientosOcupados : new HashSet<>();
    }

    // Getters y Setters...

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Set<String> getAsientosOcupados() {
        return asientosOcupados;
    }

    public void setAsientosOcupados(Set<String> asientosOcupados) {
        this.asientosOcupados = asientosOcupados;
    }

    /**
     * Calcula y retorna la lista de asientos disponibles para esta función.
     *
     * @param sala Instancia de la clase Sala asociada a esta función.
     * @return Lista de asientos disponibles.
     */
    public Set<String> getAsientosDisponibles(Sala sala) {
        Set<String> totalAsientos = generarTodosLosAsientos(sala);
        Set<String> asientosDisponibles = new HashSet<>(totalAsientos);
        asientosDisponibles.removeAll(this.asientosOcupados);
        return asientosDisponibles;
    }

    /**
     * Genera todos los asientos posibles basados en la distribución de la sala.
     *
     * @param sala Instancia de la clase Sala.
     * @return Lista de todos los asientos en la sala.
     */
    private Set<String> generarTodosLosAsientos(Sala sala) {
        Set<String> asientos = new HashSet<>();
        int filas = sala.getFilas();
        int columnas = sala.getColumnas();

        for (int fila = 0; fila < filas; fila++) {
            char letraFila = (char) ('A' + fila);
            for (int columna = 1; columna <= columnas; columna++) {
                String asiento = "" + letraFila + columna;
                asientos.add(asiento);
            }
        }

        return asientos;
    }

    /**
     * Sobrescribir el método toString() para una mejor visualización.
     */
    @Override
    public String toString() {
        return String.format("Funcion{Sala='%s', Pelicula='%s', Horario='%s', Estado='%s', Idioma='%s', AsientosOcupados=%s}",
                sala, pelicula, horario, estado, idioma, asientosOcupados.toString());
    }
}