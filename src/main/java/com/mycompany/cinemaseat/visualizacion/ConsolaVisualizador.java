package com.mycompany.cinemaseat.visualizacion;

import com.mycompany.cinemaseat.modelos.Funcion;
import com.mycompany.cinemaseat.modelos.Sala;

public class ConsolaVisualizador {

    public void mostrarMapaAsientos(Funcion funcion, Sala sala) {
        System.out.println("\nPelícula: " + funcion.getPelicula());
        System.out.println("Sala: " + funcion.getSala());
        System.out.println("Horario: " + funcion.getHorario());
        System.out.println("Idioma: " + funcion.getIdioma());
        System.out.println("Estado: " + funcion.getEstado());
        System.out.println("Distribución de Asientos:");

        int filasSTD = sala.getDistribucionAsientos().getOrDefault("STD", 0);
        int filasVIP = sala.getDistribucionAsientos().getOrDefault("VIP", 0);
        int filas4D = sala.getDistribucionAsientos().getOrDefault("4D", 0);
        int columnas = sala.getColumnas();

        int filaActual = 1;
        char columnaInicial = 'A';

        // Mostrar filas de asientos estándar
        for (int i = 0; i < filasSTD; i++) {
            imprimirFila(funcion, filaActual, "STD", columnas, columnaInicial);
            filaActual++;
        }

        // Mostrar filas de asientos VIP
        for (int i = 0; i < filasVIP; i++) {
            imprimirFila(funcion, filaActual, "VIP", columnas, columnaInicial);
            filaActual++;
        }

        // Mostrar filas de asientos 4D
        for (int i = 0; i < filas4D; i++) {
            imprimirFila(funcion, filaActual, "4D", columnas, columnaInicial);
            filaActual++;
        }
    }

    private void imprimirFila(Funcion funcion, int numeroFila, String tipoAsiento, int columnas, char columnaInicial) {
        System.out.printf("%d %s   ", numeroFila, tipoAsiento);
        for (int j = 0; j < columnas; j++) {
            String asiento = String.format("%c%d", (char) (columnaInicial + j), numeroFila);
            System.out.print(funcion.getAsientosOcupados().contains(asiento) ? "+ " : "- ");
        }
        System.out.println();
    }
}