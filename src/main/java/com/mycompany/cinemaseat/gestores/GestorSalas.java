package com.mycompany.cinemaseat.gestores;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mycompany.cinemaseat.modelos.Sala;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Clase que gestiona las operaciones relacionadas con las salas.
 */
public class GestorSalas {
    private List<Sala> salas;
    private static final String ARCHIVO_SALAS = "data/DBjson/salas.json";

    /**
     * Constructor de la clase GestorSalas.
     * Carga las salas desde el archivo JSON al instanciar el gestor.
     */
    public GestorSalas() {
        this.salas = cargarSalas();
    }

    /**
     * Carga las salas desde el archivo JSON utilizando BufferedReader.
     *
     * @return Lista de salas cargadas.
     */
    private List<Sala> cargarSalas() {
        Path path = Paths.get(ARCHIVO_SALAS);

        if (!Files.exists(path)) {
            System.out.println("Error: No se pudo encontrar el archivo de salas en la ruta: " + ARCHIVO_SALAS);
            return new ArrayList<>();
        }

        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            Gson gson = new Gson();
            Type tipoLista = new TypeToken<List<Sala>>() {}.getType();
            List<Sala> salasCargadas = gson.fromJson(reader, tipoLista);
            if (salasCargadas != null) {
                System.out.println("Salas cargadas exitosamente.");
                return salasCargadas;
            } else {
                System.out.println("No se encontraron salas en el archivo JSON.");
                return new ArrayList<>();
            }
        } catch (Exception e) {
            System.out.println("Error al cargar las salas: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Guarda las salas actuales en el archivo JSON utilizando BufferedWriter.
     */
    public void guardarSalas() {
        Path path = Paths.get(ARCHIVO_SALAS);
        Path parentDir = path.getParent();

        try {
            // Crear las carpetas necesarias si no existen
            if (parentDir != null && !Files.exists(parentDir)) {
                Files.createDirectories(parentDir);
                System.out.println("Directorios creados en: " + parentDir.toAbsolutePath());
            }

            try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
                Gson gson = new GsonBuilder()
                        .setPrettyPrinting()
                        .create();
                gson.toJson(this.salas, writer);
                System.out.println("Salas guardadas exitosamente en " + ARCHIVO_SALAS);
            }
        } catch (IOException e) {
            System.out.println("Error al guardar las salas: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Agrega una nueva sala al sistema.
     *
     * @param sala La sala a agregar.
     */
    public void agregarSala(Sala sala) {
        salas.add(sala);
        guardarSalas();
        System.out.println("Sala agregada exitosamente.");
    }

    /**
     * Busca una sala por su nombre.
     *
     * @param nombreSala El nombre de la sala a buscar.
     * @return La sala encontrada o null si no existe.
     */
    public Sala buscarSalaPorNombre(String nombreSala) {
        for (Sala sala : salas) {
            if (sala.getNombreSala().equalsIgnoreCase(nombreSala)) {
                return sala;
            }
        }
        return null;
    }

    /**
     * Obtiene la lista de salas disponibles (disponibles para nuevas funciones).
     *
     * @return Lista de salas disponibles.
     */
    public List<Sala> obtenerSalasDisponibles() {
        List<Sala> salasDisponibles = new ArrayList<>();
        for (Sala sala : salas) {
            if (sala.isDisponible()) {
                salasDisponibles.add(sala);
            }
        }
        return salasDisponibles;
    }

    /**
     * Obtiene la lista completa de salas.
     *
     * @return Una lista inmodificable de salas. Retorna una lista vacía si no hay salas.
     */
    public List<Sala> obtenerSalas() {
        if (this.salas == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(this.salas);
    }

    /**
     * Actualiza una sala existente en el sistema.
     *
     * @param salaActualizada La sala actualizada.
     */
    public void actualizarSala(Sala salaActualizada) {
        for (int i = 0; i < salas.size(); i++) {
            if (salas.get(i).getNombreSala().equalsIgnoreCase(salaActualizada.getNombreSala())) {
                salas.set(i, salaActualizada);
                guardarSalas();
                System.out.println("Sala actualizada exitosamente.");
                return;
            }
        }
        System.out.println("Error: Sala no encontrada para actualizar.");
    }

    /**
     * Elimina una sala del sistema.
     *
     * @param nombreSala El nombre de la sala a eliminar.
     */
    public void eliminarSala(String nombreSala) {
        Sala sala = buscarSalaPorNombre(nombreSala);
        if (sala == null) {
            System.out.println("Error: No se encontró una sala con el nombre proporcionado.");
            return;
        }

        salas.remove(sala);
        guardarSalas();
        System.out.println("Sala eliminada exitosamente.");
    }
}