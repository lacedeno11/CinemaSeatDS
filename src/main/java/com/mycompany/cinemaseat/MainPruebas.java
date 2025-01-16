/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cinemaseat;

import com.mycompany.cinemaseat.gestores.GestorSalas;
import com.mycompany.cinemaseat.gestores.GestorChats;
import com.mycompany.cinemaseat.gestores.GestorFunciones;
import com.mycompany.cinemaseat.gestores.GestorUsuarios;
import com.mycompany.cinemaseat.modelos.Funcion;
import com.mycompany.cinemaseat.visualizacion.ConsolaVisualizador;
import com.mycompany.cinemaseat.modelos.Sala;
import com.mycompany.cinemaseat.excepciones.UsuarioNoAutorizadoException;
import com.mycompany.cinemaseat.excepciones.AsientoNoDisponibleException;
import com.mycompany.cinemaseat.excepciones.TiempoExcedidoException;
import com.mycompany.cinemaseat.modelos.Administrador;
import com.mycompany.cinemaseat.modelos.Usuario;

import java.util.List;
import java.util.Scanner;

public class MainPruebas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestorUsuarios gestorUsuarios = new GestorUsuarios(); // Sin gestorPersistencia
        GestorSalas gestorSalas = new GestorSalas();
        GestorFunciones gestorFunciones = new GestorFunciones();

        System.out.println("--- Inicio de Sesión ---");

        // Pedir credenciales de inicio de sesión
        System.out.print("Ingrese su correo: ");
        String email = scanner.nextLine();

        System.out.print("Ingrese su contraseña: ");
        String password = scanner.nextLine();

        try {
            Usuario usuario = gestorUsuarios.iniciarSesion(email, password);

            if (usuario instanceof Administrador) {
                System.out.println("\n¡Bienvenido, Administrador!");
                menuAdministrador(scanner, gestorSalas, gestorFunciones);
            } else {
                System.out.println("El usuario no tiene permisos de administrador.");
            }
        } catch (UsuarioNoAutorizadoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void menuAdministrador(Scanner scanner, GestorSalas gestorSalas, GestorFunciones gestorFunciones) {
        int opcion;

        do {
            System.out.println("\n--- Menú Administrador ---");
            System.out.println("1. Ver Salas");
            System.out.println("2. Modificar Salas");
            System.out.println("3. Ver Funciones");
            System.out.println("4. Modificar Funciones");
            System.out.println("5. Revisar Quejas de Compras");
            System.out.println("6. Salir");

            System.out.print("Seleccione una opción: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1 -> verSalas(gestorSalas);
                case 2 -> modificarSalas(scanner, gestorSalas);
                case 3 -> verFunciones(gestorFunciones);
                case 4 -> modificarFunciones(scanner, gestorFunciones);
                case 5 -> revisarQuejas();
                case 6 -> System.out.println("Saliendo del sistema...");
                default -> System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 6);
    }

    private static void verSalas(GestorSalas gestorSalas) {
        System.out.println("\n--- Lista de Salas ---");
        for (Sala sala : gestorSalas.obtenerSalas()) {
            System.out.println("Nombre: " + sala.getNombreSala());
            System.out.println("Disponible: " + (sala.isDisponible() ? "Sí" : "No"));
            System.out.println("Filas: " + sala.getFilas());
            System.out.println("Columnas: " + sala.getColumnas());
            System.out.println("Distribución de Asientos: " + sala.getDistribucionAsientos());
            System.out.println("---");
        }
    }

    private static void modificarSalas(Scanner scanner, GestorSalas gestorSalas) {
        System.out.println("\n--- Modificar Salas ---");
        System.out.print("Ingrese el nombre de la sala a modificar: ");
        String nombreSala = scanner.nextLine();

        Sala sala = gestorSalas.buscarSalaPorNombre(nombreSala);
        if (sala != null) {
            System.out.print("¿Desea cambiar la disponibilidad de la sala? (sí/no): ");
            String cambiarDisponibilidad = scanner.nextLine();

            if (cambiarDisponibilidad.equalsIgnoreCase("sí")) {
                sala.setDisponible(!sala.isDisponible());
                System.out.println("Disponibilidad cambiada exitosamente.");
            } else {
                System.out.println("No se realizaron cambios.");
            }
        } else {
            System.out.println("Sala no encontrada.");
        }
    }

    private static void verFunciones(GestorFunciones gestorFunciones) {
        System.out.println("\n--- Lista de Funciones ---");
        for (Funcion funcion : gestorFunciones.obtenerFunciones()) {
            System.out.println("Película: " + funcion.getPelicula());
            System.out.println("Sala: " + funcion.getSala());
            System.out.println("Horario: " + funcion.getHorario());
            System.out.println("Idioma: " + funcion.getIdioma());
            System.out.println("Estado: " + funcion.getEstado());
            System.out.println("Asientos Ocupados: " +
                                funcion.getAsientosOcupados());
            System.out.println("---");
        }
    }

    private static void modificarFunciones(Scanner scanner, GestorFunciones gestorFunciones) {
        System.out.println("\n--- Modificar Funciones ---");
        System.out.print("Ingrese la sala de la función a modificar: ");
        String nombreSala = scanner.nextLine();

        Funcion funcion = gestorFunciones.buscarFuncionPorSala(nombreSala);
        if (funcion != null) {
            System.out.print("Ingrese el nuevo estado de la función (PROGRAMADA, CANCELADA): ");
            String nuevoEstado = scanner.nextLine();
            funcion.setEstado(nuevoEstado);
            System.out.println("Estado de la función actualizado exitosamente.");
        } else {
            System.out.println("Función no encontrada.");
        }
    }

    private static void revisarQuejas() {
        System.out.println("\n--- Revisar Quejas de Compras ---");
        System.out.println("Actualmente no hay quejas reportadas en el sistema.");
    }
}