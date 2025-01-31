/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cinemaseat;

import com.mycompany.cinemaseat.excepciones.UsuarioNoAutorizadoException;
import com.mycompany.cinemaseat.gestores.GestorSalas;
import com.mycompany.cinemaseat.gestores.GestorChats;
import com.mycompany.cinemaseat.gestores.GestorFunciones;
import com.mycompany.cinemaseat.gestores.GestorUsuarios;
import com.mycompany.cinemaseat.modelos.Boleto;
import com.mycompany.cinemaseat.modelos.Cliente;
import com.mycompany.cinemaseat.modelos.Usuario;
import com.mycompany.cinemaseat.visualizacion.ClienteCompraApp;
import com.mycompany.cinemaseat.visualizacion.MenuAdministrador;
import java.io.IOException;
import java.util.Arrays;

import java.util.List;
import java.util.Scanner;

public class MainPruebas {
    public static void main(String[] args) throws IOException {
        GestorFunciones gestorFunciones = new GestorFunciones();
        GestorSalas gestorSalas = new GestorSalas();
        GestorUsuarios gestorUsuarios = new GestorUsuarios();

        MenuAdministrador menuAdmin = new MenuAdministrador(gestorFunciones, gestorSalas, gestorUsuarios);
        ClienteCompraApp clienteApp = new ClienteCompraApp(gestorFunciones, gestorSalas);

        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Menú Administrador");
            System.out.println("2. Menú Cliente (Comprar Boletos)");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = leerEntero(scanner);

            switch (opcion) {
                case 1:
                    menuAdmin.mostrarMenu();
                    break;
                case 2:
                    clienteApp.iniciar();
                    break;
                case 3:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 3);
    }

    private static int leerEntero(Scanner scanner) {
        int numero;
        while (true) {
            try {
                String input = scanner.nextLine();
                numero = Integer.parseInt(input);
                if (numero < 0) {
                    System.out.print("Entrada inválida. Por favor, ingrese un número positivo: ");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Por favor, ingrese un número: ");
            }
        }
        return numero;
    }
}
