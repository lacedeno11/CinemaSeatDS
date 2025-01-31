package com.mycompany.cinemaseat.utils;

import java.util.Scanner;

public class ValidadorEntradas {

    private ValidadorEntradas() {
        // Constructor privado para evitar instanciación
    }

    /**
     * Método para leer y validar un número entero positivo desde el usuario.
     *
     * @param scanner Objeto Scanner para leer la entrada del usuario.
     * @return Un número entero positivo ingresado por el usuario.
     */
    public static int leerEntero(Scanner scanner) {
        int numero;
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                numero = Integer.parseInt(input);
                if (numero < 0) {
                    System.out.print("Entrada inválida. Por favor, ingrese un número positivo: ");
                    continue;
                }
                return numero;
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Por favor, ingrese un número válido: ");
            }
        }
    }
}
