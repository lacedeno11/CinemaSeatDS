package com.mycompany.cinemaseat.visualizacion;

import com.mycompany.cinemaseat.modelos.Funcion;
import com.mycompany.cinemaseat.modelos.Sala;

public class ConsolaVisualizador {

    /**
     * Muestra el mapa de asientos de una función y sala específicos.
     *
     * @param funcion Instancia de la clase Funcion.
     * @param sala    Instancia de la clase Sala.
     */
    public void mostrarMapaAsientos(Funcion funcion, Sala sala) {
        System.out.println("\nPelícula: " + funcion.getPelicula());
        System.out.println("Sala: " + funcion.getSala());
        System.out.println("Horario: " + funcion.getHorario());
        System.out.println("Idioma: " + funcion.getIdioma());
        System.out.println("Estado: " + funcion.getEstado());
        System.out.println("Distribución de Asientos:");

        // Obtener la distribución de asientos por tipo
        int filasSTD = sala.getDistribucionAsientos().getOrDefault("STD", 0);
        int filasVIP = sala.getDistribucionAsientos().getOrDefault("VIP", 0);
        int filas4D = sala.getDistribucionAsientos().getOrDefault("4D", 0);
        int columnas = sala.getColumnas();

        // Imprimir encabezado de columnas con indentación
        System.out.print("\t\t"); // Dos tabulaciones para alinear con las filas
        for (int i = 1; i <= columnas; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        int filaActual = 0; // Contador de filas para asignar letras
        char filaLabel;

        // Mostrar filas de asientos estándar
        for (int i = 0; i < filasSTD; i++) {
            filaLabel = (char) ('A' + filaActual);
            imprimirFila(funcion, filaLabel, "STD", columnas);
            filaActual++;
        }

        // Mostrar filas de asientos VIP
        for (int i = 0; i < filasVIP; i++) {
            filaLabel = (char) ('A' + filaActual);
            imprimirFila(funcion, filaLabel, "VIP", columnas);
            filaActual++;
        }

        // Mostrar filas de asientos 4D
        for (int i = 0; i < filas4D; i++) {
            filaLabel = (char) ('A' + filaActual);
            imprimirFila(funcion, filaLabel, "4D", columnas);
            filaActual++;
        }
    }

    /**
     * Imprime una fila específica de asientos en el mapa.
     *
     * @param funcion     Instancia de la clase Funcion.
     * @param filaLabel   Letra que representa la fila (A, B, C, etc.).
     * @param tipoAsiento Tipo de asiento (STD, VIP, 4D).
     * @param columnas    Número de columnas en la sala.
     */
    private void imprimirFila(Funcion funcion, char filaLabel, String tipoAsiento, int columnas) {
        // Formatear la etiqueta de la fila con tipo de asiento
        System.out.printf("%c %-5s ", filaLabel, tipoAsiento);

        // Iterar sobre cada columna para mostrar el estado del asiento
        for (int j = 0; j < columnas; j++) {
            // Generar la etiqueta del asiento, por ejemplo, A1, A2, etc.
            String asiento = String.format("%c%d", filaLabel, j + 1);

            // Mostrar "+" si el asiento está ocupado, "-" si está disponible
            System.out.print(funcion.getAsientosOcupados().contains(asiento) ? "+ " : "- ");
        }
        System.out.println(); // Saltar a la siguiente línea después de la fila
    }
}