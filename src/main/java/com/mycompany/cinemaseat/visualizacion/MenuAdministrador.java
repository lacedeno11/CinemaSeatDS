package com.mycompany.cinemaseat.visualizacion;

import com.mycompany.cinemaseat.gestores.GestorFunciones;
import com.mycompany.cinemaseat.gestores.GestorSalas;
import com.mycompany.cinemaseat.gestores.GestorUsuarios;
import com.mycompany.cinemaseat.modelos.Funcion;
import com.mycompany.cinemaseat.modelos.Sala;
import com.mycompany.cinemaseat.modelos.Usuario;
import com.mycompany.cinemaseat.modelos.Boleto;
import com.mycompany.cinemaseat.modelos.Cliente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Clase que maneja el menú de opciones disponibles para un administrador.
 */
public class MenuAdministrador {
    private GestorFunciones gestorFunciones;
    private GestorSalas gestorSalas;
    private GestorUsuarios gestorUsuarios;
    private ConsolaVisualizador consolaVisualizador; // Instancia de ConsolaVisualizador

    /**
     * Constructor de la clase MenuAdministrador.
     *
     * @param gestorFunciones Instancia de GestorFunciones para gestionar funciones.
     * @param gestorSalas      Instancia de GestorSalas para gestionar salas.
     * @param gestorUsuarios   Instancia de GestorUsuarios para gestionar usuarios.
     */
    public MenuAdministrador(GestorFunciones gestorFunciones, GestorSalas gestorSalas, GestorUsuarios gestorUsuarios) {
        this.gestorFunciones = gestorFunciones;
        this.gestorSalas = gestorSalas;
        this.gestorUsuarios = gestorUsuarios;
        this.consolaVisualizador = new ConsolaVisualizador(); // Inicializar ConsolaVisualizador
    }

    /**
     * Muestra el menú principal del administrador y maneja las opciones seleccionadas.
     */
    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\n--- Menú Administrador ---");
            System.out.println("1. Ver Funciones");
            System.out.println("2. Agregar Función");
            System.out.println("3. Modificar Función");
            System.out.println("4. Eliminar Función");
            System.out.println("5. Ver Usuarios");
            System.out.println("6. Agregar Boleto a Usuario");
            System.out.println("7. Ver Salas");
            System.out.println("8. Agregar Sala");
            System.out.println("9. Eliminar Sala");
            System.out.println("10. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = leerEntero(scanner);

            switch (opcion) {
                case 1:
                    verFunciones();
                    break;
                case 2:
                    agregarFuncion(scanner);
                    break;
                case 3:
                    modificarFuncion(scanner);
                    break;
                case 4:
                    eliminarFuncion(scanner);
                    break;
                case 5:
                    verUsuarios();
                    break;
                case 6:
                    agregarBoletoAUsuario(scanner);
                    break;
                case 7:
                    verSalas();
                    break;
                case 8:
                    agregarSala(scanner);
                    break;
                case 9:
                    eliminarSala(scanner);
                    break;
                case 10:
                    System.out.println("Saliendo del menú de administrador...");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
            }
        } while (opcion != 10);
    }

    /**
     * Muestra todas las funciones registradas en el sistema.
     */
/**
 * Muestra todas las funciones registradas en el sistema.
 */
private void verFunciones() {
    List<Funcion> funciones = gestorFunciones.obtenerFunciones();
    if (funciones.isEmpty()) {
        System.out.println("No hay funciones registradas.");
        return;
    }
    System.out.println("\n--- Funciones Registradas ---");
    for (Funcion funcion : funciones) {
        // Mostrar Sala en lugar de ID
        System.out.printf("Sala: %s, Película: %s, Horario: %s, Estado: %s, Idioma: %s\n",
                funcion.getSala(), funcion.getPelicula(),
                funcion.getHorario(), funcion.getEstado(), funcion.getIdioma());
    }
}

    /**
     * Permite al administrador agregar una nueva función.
     *
     * @param scanner Objeto Scanner para leer la entrada del usuario.
     */
    private void agregarFuncion(Scanner scanner) {
        System.out.println("\n--- Agregar Nueva Función ---");
        System.out.println("Salas disponibles:");
        List<Sala> salasDisponibles = gestorSalas.obtenerSalasDisponibles();
        if (salasDisponibles.isEmpty()) {
            System.out.println("No hay salas disponibles para agregar una función.");
            return;
        }
        for (int i = 0; i < salasDisponibles.size(); i++) {
            Sala sala = salasDisponibles.get(i);
            System.out.printf("%d. %s (Filas: %d, Columnas: %d, Distribución: %s)\n",
                    i + 1, sala.getNombreSala(), sala.getFilas(), sala.getColumnas(), sala.getDistribucionAsientos());
        }
        System.out.print("Ingrese el número de la sala: ");
        int numeroSala = leerEntero(scanner);

        if (numeroSala < 1 || numeroSala > salasDisponibles.size()) {
            System.out.println("Número de sala inválido.");
            return;
        }

        Sala salaSeleccionada = salasDisponibles.get(numeroSala - 1);

        System.out.print("Ingrese la película: ");
        String pelicula = scanner.nextLine();
        System.out.print("Ingrese el horario (formato YYYY-MM-DD HH:MM): ");
        String horario = scanner.nextLine();
        System.out.print("Ingrese el estado (ej. Programada, Cancelada): ");
        String estado = scanner.nextLine();
        System.out.print("Ingrese el idioma: ");
        String idioma = scanner.nextLine();

        Set<String> asientosOcupados = new HashSet<>();
        Funcion nuevaFuncion = new Funcion(salaSeleccionada.getNombreSala(), pelicula, horario, estado, idioma, asientosOcupados);
        gestorFunciones.agregarFuncion(nuevaFuncion);
        salaSeleccionada.setDisponible(false);
        gestorSalas.actualizarSala(salaSeleccionada);
    }

    /**
     * Permite al administrador modificar una función existente.
     *
     * @param scanner Objeto Scanner para leer la entrada del usuario.
     */
    private void modificarFuncion(Scanner scanner) {
        System.out.println("\n--- Modificar Función ---");
        List<Funcion> funciones = gestorFunciones.obtenerFunciones();
        if (funciones.isEmpty()) {
            System.out.println("No hay funciones registradas para modificar.");
            return;
        }

        System.out.println("Funciones disponibles:");
        for (int i = 0; i < funciones.size(); i++) {
            Funcion funcion = funciones.get(i);
           // Mostrar Sala en lugar de ID
               System.out.printf("%d. Sala: %s, Película: %s, Horario: %s\n",
                   i + 1, funcion.getSala(), funcion.getPelicula(), funcion.getHorario());
        }

        System.out.print("Seleccione el número de la función a modificar: ");
        int seleccion = leerEntero(scanner);

         if (seleccion < 1 || seleccion > funciones.size()) {
            System.out.println("Selección inválida.");
            return;
        }

        Funcion funcionSeleccionada = funciones.get(seleccion - 1);

        System.out.printf("Modificando función: Sala: %s, Película: %s, Horario: %s\n",
             funcionSeleccionada.getSala(), funcionSeleccionada.getPelicula(), funcionSeleccionada.getHorario());
    
        System.out.print("Ingrese el nuevo horario (o presione Enter para no cambiarlo): ");
         String nuevaHora = scanner.nextLine();
         if (nuevaHora.isEmpty()) nuevaHora = null;
         System.out.print("Ingrese el nuevo estado (o presione Enter para no cambiarlo): ");
         String nuevoEstado = scanner.nextLine();
         if (nuevoEstado.isEmpty()) nuevoEstado = null;
         System.out.print("¿Desea cancelar la función? (sí/no): ");
          String cancelar = scanner.nextLine();
          boolean cancelarFuncion = cancelar.equalsIgnoreCase("sí") || cancelar.equalsIgnoreCase("si");

          gestorFunciones.modificarFuncion(funcionSeleccionada.getId(), nuevaHora, nuevoEstado, cancelarFuncion);
    }
    /**
     * Permite al administrador eliminar una función existente.
     *
     * @param scanner Objeto Scanner para leer la entrada del usuario.
     */
    private void eliminarFuncion(Scanner scanner) {
    System.out.println("\n--- Eliminar Función ---");
    List<Funcion> funciones = gestorFunciones.obtenerFunciones();
    if (funciones.isEmpty()) {
        System.out.println("No hay funciones registradas para eliminar.");
        return;
    }

    System.out.println("Funciones disponibles:");
    for (int i = 0; i < funciones.size(); i++) {
        Funcion funcion = funciones.get(i);
        // Mostrar Sala en lugar de ID
        System.out.printf("%d. Sala: %s, Película: %s, Horario: %s\n",
                i + 1, funcion.getSala(), funcion.getPelicula(), funcion.getHorario());
    }

    System.out.print("Seleccione el número de la función a eliminar: ");
    int seleccion = leerEntero(scanner);

    if (seleccion < 1 || seleccion > funciones.size()) {
        System.out.println("Selección inválida.");
        return;
    }

    Funcion funcionSeleccionada = funciones.get(seleccion - 1);

    System.out.printf("¿Está seguro de que desea eliminar la función: Sala: %s, Película: %s, Horario: %s? (sí/no): ",
            funcionSeleccionada.getSala(), funcionSeleccionada.getPelicula(), funcionSeleccionada.getHorario());
    String confirmacion = scanner.nextLine();
    if (confirmacion.equalsIgnoreCase("sí") || confirmacion.equalsIgnoreCase("si")) {
        gestorFunciones.eliminarFuncion(funcionSeleccionada.getId());

        // Actualizar disponibilidad de la sala asociada
        Sala salaAsociada = gestorSalas.buscarSalaPorNombre(funcionSeleccionada.getSala());
        if (salaAsociada != null) {
            salaAsociada.setDisponible(true);
            gestorSalas.actualizarSala(salaAsociada);
        }
    } else {
        System.out.println("Eliminación cancelada.");
    }
}

    /**
     * Muestra todos los usuarios registrados en el sistema.
     */
    private void verUsuarios() {
        System.out.println("\n--- Usuarios Registrados ---");
        List<Usuario> usuarios = gestorUsuarios.obtenerUsuarios();
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
            return;
        }
        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
        }
    }

    /**
     * Permite al administrador agregar un boleto a un usuario específico.
     *
     * @param scanner Objeto Scanner para leer la entrada del usuario.
     */
    private void agregarBoletoAUsuario(Scanner scanner) {
    System.out.println("\n--- Agregar Boleto a Usuario ---");
    System.out.print("Ingrese el email del usuario: ");
    String emailUsuario = scanner.nextLine().trim();

    Usuario usuario = gestorUsuarios.buscarUsuario(emailUsuario);
    if (usuario == null) {
        System.out.println("No se encontró un usuario con el email proporcionado.");
        return;
    }

    if (!(usuario instanceof Cliente)) {
        System.out.println("Solo los usuarios de tipo Cliente pueden tener boletos asignados.");
        return;
    }

    List<Funcion> funciones = gestorFunciones.obtenerFunciones();
    if (funciones.isEmpty()) {
        System.out.println("No hay funciones registradas para asignar un boleto.");
        return;
    }

    System.out.println("Funciones disponibles:");
    for (int i = 0; i < funciones.size(); i++) {
        Funcion funcion = funciones.get(i);
        String disponibilidad = funcion.getEstado().equalsIgnoreCase("PROGRAMADA") ? "Sí" : "No";
        // Mostrar Sala en lugar de ID
        System.out.printf("%d. Sala: %s, Película: %s, Horario: %s, Disponible: %s\n",
                i + 1, funcion.getSala(), funcion.getPelicula(), funcion.getHorario(), disponibilidad);
    }

    System.out.print("Seleccione el número de la función: ");
    int seleccion = leerEntero(scanner);

    if (seleccion < 1 || seleccion > funciones.size()) {
        System.out.println("Selección inválida.");
        return;
    }

    Funcion funcionSeleccionada = funciones.get(seleccion - 1);

    if (!funcionSeleccionada.getEstado().equalsIgnoreCase("PROGRAMADA")) {
        System.out.println("La función seleccionada no está disponible para asignar boletos.");
        return;
    }

    // Obtener la sala asociada a la función
    Sala salaAsociada = gestorSalas.buscarSalaPorNombre(funcionSeleccionada.getSala());
    if (salaAsociada == null) {
        System.out.println("Error: No se encontró la sala asociada a la función.");
        return;
    }

    // Mostrar el mapa de asientos utilizando ConsolaVisualizador
    consolaVisualizador.mostrarMapaAsientos(funcionSeleccionada, salaAsociada);

    System.out.print("Ingrese los números de asiento que desea asignar al cliente (ej. A1,A2,A3): ");
    String asientosInput = scanner.nextLine().trim().toUpperCase();

    // Separar los asientos ingresados por comas o espacios
    String[] asientosArray = asientosInput.split("[,\\s]+");
    Set<String> asientosIngresados = new HashSet<>();
    for (String asiento : asientosArray) {
        asientosIngresados.add(asiento.trim());
    }

    // Validar todos los asientos ingresados
    Set<String> asientosDisponibles = funcionSeleccionada.getAsientosDisponibles(salaAsociada);
    List<String> asientosInvalidos = new ArrayList<>();
    for (String asiento : asientosIngresados) {
        // Validar formato (una letra seguida de uno o más números)
        if (!asiento.matches("^[A-Z]\\d+$")) {
            asientosInvalidos.add(asiento);
            continue;
        }

        // Verificar si el asiento existe en la sala
        Set<String> todosAsientos = funcionSeleccionada.getAsientosDisponibles(salaAsociada);
        if (!todosAsientos.contains(asiento) && !funcionSeleccionada.getAsientosOcupados().contains(asiento)) {
            asientosInvalidos.add(asiento);
            continue;
        }

        // Verificar si el asiento está disponible
        if (!asientosDisponibles.contains(asiento)) {
            asientosInvalidos.add(asiento);
        }
    }

    if (!asientosInvalidos.isEmpty()) {
        System.out.println("Los siguientes asientos son inválidos o no están disponibles:");
        for (String asiento : asientosInvalidos) {
            System.out.println("- " + asiento);
        }
        return;
    }

    // Asignar todos los asientos ingresados
    funcionSeleccionada.getAsientosOcupados().addAll(asientosIngresados);
    gestorFunciones.guardarFunciones(); // Guardar cambios en las funciones

    // Calcular el subtotal para todos los asientos
    double subtotal = calcularSubtotal(salaAsociada, asientosIngresados);

    // Crear y asignar el boleto al usuario
    Boleto nuevoBoleto = new Boleto(funcionSeleccionada.getId(), new ArrayList<>(asientosIngresados), subtotal);
    gestorUsuarios.agregarBoletoAUsuario(emailUsuario, nuevoBoleto);

    System.out.println("Boletos asignados exitosamente al usuario: " + ((Cliente) usuario).getNombre());
}
    /**
     * Calcula el subtotal del boleto basado en el tipo de asiento.
     *
     * @param sala    Instancia de la clase Sala.
     * @param asiento Número de asiento (ej. A1).
     * @return Subtotal del boleto.
     */
/**
 * Calcula el subtotal para una lista de asientos asignados.
 *
 * @param sala            Instancia de la clase Sala.
 * @param asientosAsignados Conjunto de asientos asignados.
 * @return Subtotal total.
 */
private double calcularSubtotal(Sala sala, Set<String> asientosAsignados) {
    double subtotal = 0.0;
    // Definir precios por tipo de asiento
    Map<String, Double> preciosPorTipo = new HashMap<>();
    preciosPorTipo.put("STD", 10.0); // Precio estándar
    preciosPorTipo.put("VIP", 15.0); // Precio VIP
    preciosPorTipo.put("4D", 20.0);  // Precio 4D

    for (String asiento : asientosAsignados) {
        // Determinar el tipo de asiento basado en la fila
        char fila = asiento.charAt(0);
        int filaIndex = fila - 'A'; // Suponiendo que 'A' es la primera fila
        String tipoAsiento = determinarTipoAsiento(filaIndex, sala.getDistribucionAsientos());
        double precio = preciosPorTipo.getOrDefault(tipoAsiento, 10.0); // Precio por defecto
        subtotal += precio;
    }

    return subtotal;
}

/**
 * Determina el tipo de asiento basado en el índice de la fila y la distribución de asientos.
 *
 * @param filaIndex Índice de la fila (0 para 'A', 1 para 'B', etc.).
 * @param distribucionDistribucion de asientos de la sala.
 * @return Tipo de asiento (STD, VIP, 4D).
 */
private String determinarTipoAsiento(int filaIndex, Map<String, Integer> distribucionAsientos) {
    int acumulado = 0;
    for (Map.Entry<String, Integer> entry : distribucionAsientos.entrySet()) {
        String tipo = entry.getKey();
        int filasTipo = entry.getValue();
        if (filaIndex < acumulado + filasTipo) {
            return tipo;
        }
        acumulado += filasTipo;
    }
    return "STD"; // Tipo por defecto
}

    /**
     * Permite al administrador agregar una nueva sala.
     *
     * @param scanner Objeto Scanner para leer la entrada del usuario.
     */
    private void agregarSala(Scanner scanner) {
        System.out.println("\n--- Agregar Nueva Sala ---");
        System.out.print("Ingrese el nombre de la sala: ");
        String nombreSala = scanner.nextLine();

        if (gestorSalas.buscarSalaPorNombre(nombreSala) != null) {
            System.out.println("Error: Ya existe una sala con el nombre proporcionado.");
            return;
        }

        System.out.print("Ingrese el número de filas de la sala: ");
        int filas = leerEntero(scanner);
        if (filas <= 0) {
            System.out.println("El número de filas debe ser un número positivo.");
            return;
        }

        System.out.print("Ingrese el número de columnas de la sala: ");
        int columnas = leerEntero(scanner);
        if (columnas <= 0) {
            System.out.println("El número de columnas debe ser un número positivo.");
            return;
        }

        // Obtener distribución de asientos
        System.out.println("Ingrese la distribución de asientos.");
        Map<String, Integer> distribucionAsientos = ingresarDistribucionAsientos(scanner, filas, columnas);

        Sala nuevaSala = new Sala(nombreSala, filas, columnas, true, distribucionAsientos);
        gestorSalas.agregarSala(nuevaSala);
    }

    /**
     * Método auxiliar para ingresar la distribución de asientos.
     *
     * @param scanner  Objeto Scanner para leer la entrada del usuario.
     * @param filas    Número de filas de la sala.
     * @param columnas Número de columnas de la sala.
     * @return Mapa con la distribución de asientos.
     */
    private Map<String, Integer> ingresarDistribucionAsientos(Scanner scanner, int filas, int columnas) {
        java.util.HashMap<String, Integer> distribucion = new java.util.HashMap<>();

        // Por simplicidad, se solicitarán tres tipos de asientos: STD, VIP, 4D
        // Puedes modificar esto según las necesidades
        System.out.print("Ingrese la cantidad de asientos STD: ");
        int std = leerEntero(scanner);
        distribucion.put("STD", std);

        System.out.print("Ingrese la cantidad de asientos VIP: ");
        int vip = leerEntero(scanner);
        distribucion.put("VIP", vip);

        System.out.print("Ingrese la cantidad de asientos 4D: ");
        int d4 = leerEntero(scanner);
        distribucion.put("4D", d4);

        // Validar que la suma de asientos no exceda la capacidad total
        int totalAsientosIngresados = std + vip + d4;
        int capacidadTotal = filas * columnas;
        if (totalAsientosIngresados > capacidadTotal) {
            System.out.println("Error: La suma de asientos ingresados (" + totalAsientosIngresados +
                    ") excede la capacidad total de la sala (" + capacidadTotal + ").");
            // Repetir el ingreso de distribución
            return ingresarDistribucionAsientos(scanner, filas, columnas);
        }

        return distribucion;
    }

    /**
     * Permite al administrador eliminar una sala existente.
     *
     * @param scanner Objeto Scanner para leer la entrada del usuario.
     */
    private void eliminarSala(Scanner scanner) {
        System.out.println("\n--- Eliminar Sala ---");
        List<Sala> salas = gestorSalas.obtenerSalas();
        if (salas.isEmpty()) {
            System.out.println("No hay salas registradas para eliminar.");
            return;
        }

        System.out.println("Salas disponibles:");
        for (int i = 0; i < salas.size(); i++) {
            Sala sala = salas.get(i);
            System.out.printf("%d. %s (Filas: %d, Columnas: %d, Distribución: %s)\n",
                    i + 1, sala.getNombreSala(), sala.getFilas(), sala.getColumnas(), sala.getDistribucionAsientos());
        }

        System.out.print("Seleccione el número de la sala a eliminar: ");
        int seleccion = leerEntero(scanner);

        if (seleccion < 1 || seleccion > salas.size()) {
            System.out.println("Selección inválida.");
            return;
        }

        Sala salaSeleccionada = salas.get(seleccion - 1);

        // Verificar si hay funciones asociadas a la sala
        List<Funcion> funcionesAsociadas = gestorFunciones.buscarFuncionesPorSala(salaSeleccionada.getNombreSala());
        if (!funcionesAsociadas.isEmpty()) {
            System.out.println("Error: Hay funciones asociadas a esta sala. Elimine o reasigne las funciones antes de eliminar la sala.");
            return;
        }

        System.out.printf("¿Está seguro de que desea eliminar la sala: %s? (sí/no): ", salaSeleccionada.getNombreSala());
        String confirmacion = scanner.nextLine();
        if (confirmacion.equalsIgnoreCase("sí") || confirmacion.equalsIgnoreCase("si")) {
            gestorSalas.eliminarSala(salaSeleccionada.getNombreSala());
        } else {
            System.out.println("Eliminación cancelada.");
        }
    }

    /**
     * Permite al administrador ver todas las salas registradas.
     */
    private void verSalas() {
        System.out.println("\n--- Salas Registradas ---");
        List<Sala> salas = gestorSalas.obtenerSalas();
        if (salas.isEmpty()) {
            System.out.println("No hay salas registradas.");
            return;
        }
        for (Sala sala : salas) {
            System.out.printf("Nombre: %s, Filas: %d, Columnas: %d, Disponible: %s, Distribución Asientos: %s\n",
                    sala.getNombreSala(), sala.getFilas(), sala.getColumnas(),
                    sala.isDisponible() ? "Sí" : "No", sala.getDistribucionAsientos());
        }
    }

    /**
     * Método auxiliar para leer un entero del usuario con validación.
     *
     * @param scanner Objeto Scanner para leer la entrada.
     * @return El entero ingresado por el usuario.
     */
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
}