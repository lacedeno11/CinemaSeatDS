/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cinemaseat.visualizacion;

/**
 *
 * @author abrahamcedeno
 */
import com.mycompany.cinemaseat.gestores.GestorChats;
import com.mycompany.cinemaseat.gestores.GestorSalas;
import com.mycompany.cinemaseat.modelos.Sala;

import java.util.Scanner;

public class MenuAdministrador {
    private GestorChats gestorChats;
    private GestorSalas gestorSalas;

    public MenuAdministrador(GestorSalas gestorSalas, GestorChats gestorChats) {
        this.gestorChats = gestorChats;
        this.gestorSalas = gestorSalas;
    }

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- Menú del Administrador ---");
            System.out.println("1. Ver funciones y asientos disponibles");
            System.out.println("2. Modificar funciones de la sala");
            System.out.println("3. Revisar mensajes de clientes");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1 -> verFunciones();
                case 2 -> modificarFuncion(scanner);
                case 3 -> gestorChats.mostrarChats();
                case 4 -> System.out.println("Cerrando sesión de administrador...");
                default -> System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 4);
    }

    private void verFunciones() {
        System.out.println("\n--- Funciones y Asientos Disponibles ---");
        for (Sala sala : gestorSalas.obtenerSalas()) {
            System.out.println("Sala: " + sala.getNombreSala());
            //sala.notificarObservadores("Visualización actual de la sala");
        }
    }

    private void modificarFuncion(Scanner scanner) {
        System.out.print("\nIngrese el nombre de la sala: ");
        String nombreSala = scanner.nextLine();
        Sala sala = gestorSalas.buscarSalaPorNombre(nombreSala);

        if (sala == null) {
            System.out.println("Sala no encontrada.");
            return;
        }

        System.out.println("Opciones de modificación:");
        System.out.println("1. Cambiar horario de función");
        System.out.println("2. Cancelar función");
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1 -> {
                System.out.print("Ingrese el nuevo horario (ej. 20:00): ");
                String nuevoHorario = scanner.nextLine();
                //sala.retrasarFuncion(nuevoHorario);
            }
            //
            //case 2 -> sala.cancelarFuncion();
            default -> System.out.println("Opción inválida.");
        }
    }
}