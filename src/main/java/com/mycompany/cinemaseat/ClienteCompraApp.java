/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cinemaseat;

import com.mycompany.cinemaseat.gestores.GestorFunciones;
import com.mycompany.cinemaseat.gestores.GestorSalas;
import com.mycompany.cinemaseat.modelos.Funcion;
import com.mycompany.cinemaseat.modelos.Sala;
import com.mycompany.cinemaseat.modelos.Boleto;
import com.mycompany.cinemaseat.excepciones.AsientoNoDisponibleException;
import com.mycompany.cinemaseat.visualizacion.ConsolaVisualizador;

import java.util.*;

/**
 *
 * @author LENOVO
 */
public class ClienteCompraApp {
    private GestorFunciones gestorFunciones;
    private GestorSalas gestorSalas;
    private ConsolaVisualizador consolaVisualizador;

    public ClienteCompraApp(GestorFunciones gestorFunciones, GestorSalas gestorSalas) {
        this.gestorFunciones = gestorFunciones;
        this.gestorSalas = gestorSalas;
        this.consolaVisualizador = new ConsolaVisualizador();
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);

        // Mostrar funciones disponibles
        List<Funcion> funciones = gestorFunciones.obtenerFunciones();
        List<Funcion> funcionesDisponibles = new ArrayList<>();

        System.out.println("\n--- Funciones Disponibles ---");
        int index = 1;
        for (Funcion funcion : funciones) {
            if (funcion.getEstado().equalsIgnoreCase("Programada")) {
                System.out.printf("%d. Sala: %s | Película: %s | Horario: %s | Idioma: %s\n",
                        index++, funcion.getSala(), funcion.getPelicula(), funcion.getHorario(), funcion.getIdioma());
                funcionesDisponibles.add(funcion);
            }
        }

        if (funcionesDisponibles.isEmpty()) {
            System.out.println("No hay funciones disponibles para la compra.");
            return;
        }

        // Seleccionar función
        System.out.print("Seleccione el número de la función que desea ver: ");
        int seleccionFuncion = leerEntero(scanner);

        if (seleccionFuncion < 1 || seleccionFuncion > funcionesDisponibles.size()) {
            System.out.println("Selección inválida.");
            return;
        }

        Funcion funcionSeleccionada = funcionesDisponibles.get(seleccionFuncion - 1);
        Sala salaAsociada = gestorSalas.buscarSalaPorNombre(funcionSeleccionada.getSala());
        if (salaAsociada == null) {
            System.out.println("Error: No se encontró la sala asociada a la función seleccionada.");
            return;
        }

        // Mostrar mapa de asientos
        consolaVisualizador.mostrarMapaAsientos(funcionSeleccionada, salaAsociada);

        // Seleccionar asientos
        System.out.print("Ingrese los números de asientos que desea comprar (ej. A1,A2,A3): ");
        String asientosInput = scanner.nextLine().trim().toUpperCase();

        // Separar los asientos ingresados por comas o espacios
        String[] asientosArray = asientosInput.split("[,\\s]+");
        Set<String> asientosSeleccionados = new HashSet<>(Arrays.asList(asientosArray));

        // Validar asientos seleccionados
        try {
            validarAsientosDisponibles(asientosSeleccionados, funcionSeleccionada, salaAsociada);
        } catch (AsientoNoDisponibleException e) {
            System.out.println(e.getMessage());
            return;
        }

        // Simulación aleatoria de éxito o fallo de compra
        if (!simularCompra()) {
            System.out.println("Error: La compra no se pudo completar debido a un problema interno. Por favor, intente de nuevo.");
            return;
        }

        // Confirmar compra
        completarCompra(asientosSeleccionados, funcionSeleccionada);
        System.out.println("Compra completada con éxito. ¡Disfrute su función!");
    }

    private void validarAsientosDisponibles(Set<String> asientosSeleccionados, Funcion funcion, Sala sala) throws AsientoNoDisponibleException {
        Set<String> asientosDisponibles = funcion.getAsientosDisponibles(sala);
        for (String asiento : asientosSeleccionados) {
            if (!asientosDisponibles.contains(asiento)) {
                throw new AsientoNoDisponibleException("El asiento " + asiento + " no está disponible.");
            }
        }
    }

    private boolean simularCompra() {
        Random random = new Random();
        return random.nextBoolean();
    }

    private void completarCompra(Set<String> asientosSeleccionados, Funcion funcion) {
        funcion.getAsientosOcupados().addAll(asientosSeleccionados);
        gestorFunciones.guardarFunciones();
    }

    private int leerEntero(Scanner scanner) {
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

    public static void main(String[] args) {
        GestorFunciones gestorFunciones = new GestorFunciones();
        GestorSalas gestorSalas = new GestorSalas();

        ClienteCompraApp app = new ClienteCompraApp(gestorFunciones, gestorSalas);
        app.iniciar();
    }
}
