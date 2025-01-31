package com.mycompany.cinemaseat.visualizacion;

import com.mycompany.cinemaseat.gestores.GestorFunciones;
import com.mycompany.cinemaseat.gestores.GestorSalas;
import com.mycompany.cinemaseat.modelos.Funcion;
import com.mycompany.cinemaseat.modelos.Sala;
import com.mycompany.cinemaseat.excepciones.AsientoNoDisponibleException;
import com.mycompany.cinemaseat.utils.ValidadorEntradas;

import java.util.*;

public class ClienteCompraApp {
    private GestorFunciones gestorFunciones;
    private GestorSalas gestorSalas;
    private ConsolaVisualizador consolaVisualizador;
    private Scanner scanner;

    public ClienteCompraApp(GestorFunciones gestorFunciones, GestorSalas gestorSalas) {
        this.gestorFunciones = gestorFunciones;
        this.gestorSalas = gestorSalas;
        this.consolaVisualizador = new ConsolaVisualizador();
        this.scanner = new Scanner(System.in); 
    }

    public void iniciar() {
        List<Funcion> funcionesDisponibles = obtenerFuncionesDisponibles();
        if (funcionesDisponibles.isEmpty()) {
            System.out.println("No hay funciones disponibles para la compra.");
            return;
        }

        Funcion funcionSeleccionada = seleccionarFuncion(funcionesDisponibles);
        if (funcionSeleccionada == null) return;

        Sala salaAsociada = obtenerSalaAsociada(funcionSeleccionada);
        if (salaAsociada == null) return;

        consolaVisualizador.mostrarMapaAsientos(funcionSeleccionada, salaAsociada);

        Set<String> asientosSeleccionados = seleccionarAsientos();
        if (!validarAsientos(asientosSeleccionados, funcionSeleccionada, salaAsociada)) return;

        if (!simularCompra()) {
            System.out.println("Error: La compra no se pudo completar debido a un problema interno. Por favor, intente de nuevo.");
            return;
        }

        completarCompra(asientosSeleccionados, funcionSeleccionada);
        System.out.println("Compra completada con éxito. ¡Disfrute su función!");
    }

    private List<Funcion> obtenerFuncionesDisponibles() {
        List<Funcion> funciones = gestorFunciones.obtenerFunciones();
        List<Funcion> disponibles = new ArrayList<>();
        System.out.println("\n--- Funciones Disponibles ---");

        int index = 1;
        for (Funcion funcion : funciones) {
            if (funcion.getEstado().equalsIgnoreCase("Programada")) {
                System.out.printf("%d. Sala: %s | Película: %s | Horario: %s | Idioma: %s\n",
                        index++, funcion.getSala(), funcion.getPelicula(), funcion.getHorario(), funcion.getIdioma());
                disponibles.add(funcion);
            }
        }
        return disponibles;
    }

    private Funcion seleccionarFuncion(List<Funcion> funcionesDisponibles) {
        System.out.print("Seleccione el número de la función que desea ver: ");
        int seleccion = ValidadorEntradas.leerEntero(scanner); // Se usa el nuevo método

        if (seleccion < 1 || seleccion > funcionesDisponibles.size()) {
            System.out.println("Selección inválida.");
            return null;
        }
        return funcionesDisponibles.get(seleccion - 1);
    }

    private Sala obtenerSalaAsociada(Funcion funcionSeleccionada) {
        Sala sala = gestorSalas.buscarSalaPorNombre(funcionSeleccionada.getSala());
        if (sala == null) {
            System.out.println("Error: No se encontró la sala asociada a la función seleccionada.");
        }
        return sala;
    }

    private Set<String> seleccionarAsientos() {
        System.out.print("Ingrese los números de asientos que desea comprar (ej. A1,A2,A3): ");
        String asientosInput = scanner.nextLine().trim().toUpperCase();
        return new HashSet<>(Arrays.asList(asientosInput.split("[,\\s]+")));
    }

    private boolean validarAsientos(Set<String> asientosSeleccionados, Funcion funcion, Sala sala) {
        try {
            validarAsientosDisponibles(asientosSeleccionados, funcion, sala);
        } catch (AsientoNoDisponibleException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
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
        return new Random().nextBoolean();
    }

    private void completarCompra(Set<String> asientosSeleccionados, Funcion funcion) {
        funcion.getAsientosOcupados().addAll(asientosSeleccionados);
        gestorFunciones.guardarFunciones();
    }
}
