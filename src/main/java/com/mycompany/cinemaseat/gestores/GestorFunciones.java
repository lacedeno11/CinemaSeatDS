/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cinemaseat.gestores;

/**
 *
 * @author abrahamcedeno
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mycompany.cinemaseat.modelos.Funcion;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class GestorFunciones {
    private List<Funcion> funciones;
    private static final String ARCHIVO_FUNCIONES = "data/DBjson/funciones.json";

    public GestorFunciones() {
        this.funciones = cargarFunciones();
    }

    private List<Funcion> cargarFunciones() {
        Path path = Paths.get(ARCHIVO_FUNCIONES);

        if (!Files.exists(path)) {
            System.out.println("Error: No se pudo encontrar el archivo de funciones en la ruta: " + ARCHIVO_FUNCIONES);
            return new ArrayList<>();
        }

        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            Gson gson = new Gson();
            Type tipoLista = new TypeToken<List<Funcion>>() {}.getType();
            List<Funcion> funcionesCargadas = gson.fromJson(reader, tipoLista);
            if (funcionesCargadas != null) {
                System.out.println("Funciones cargadas exitosamente.");
                return funcionesCargadas;
            } else {
                System.out.println("No se encontraron funciones en el archivo JSON.");
                return new ArrayList<>();
            }
        } catch (Exception e) {
            System.out.println("Error al cargar las funciones: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<Funcion> obtenerFunciones() {
        return funciones;
    }

    public List<Funcion> buscarFuncionesPorSala(String nombreSala) {
        List<Funcion> funcionesPorSala = new ArrayList<>();
        for (Funcion funcion : funciones) {
            if (funcion.getSala().equalsIgnoreCase(nombreSala)) {
                funcionesPorSala.add(funcion);
            }
        }
        return funcionesPorSala;
    }
    
    public Funcion buscarFuncion(String idFuncion) {
        for (Funcion funcion : funciones) {
            if (funcion.getId().equalsIgnoreCase(idFuncion)) {
                return funcion;
            }
        }
        return null;
    }

    public void agregarFuncion(Funcion nuevaFuncion) {
        if (buscarFuncion(nuevaFuncion.getId()) == null) {
            funciones.add(nuevaFuncion);
            guardarFunciones();
            System.out.println("Función agregada exitosamente.");
        } else {
            System.out.println("Error: Ya existe una función con el mismo ID.");
        }
    }

    public void modificarFuncion(String idFuncion, String nuevaHora, String nuevoEstado, boolean cancelarFuncion) {
        Funcion funcion = buscarFuncion(idFuncion);
        if (funcion == null) {
            System.out.println("No se encontró una función con el ID proporcionado.");
            return;
        }

        if (nuevaHora != null) {
            funcion.setHorario(nuevaHora);
        }
        if (nuevoEstado != null) {
            funcion.setEstado(nuevoEstado);
        }
        if (cancelarFuncion) {
            funcion.setEstado("Cancelada");
        }

        guardarFunciones();
        System.out.println("Función modificada exitosamente.");
    }


    public void guardarFunciones() {
        Path path = Paths.get(ARCHIVO_FUNCIONES);
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
                gson.toJson(this.funciones, writer);
                System.out.println("Funciones guardadas exitosamente en " + ARCHIVO_FUNCIONES);
            }
        } catch (IOException e) {
            System.out.println("Error al guardar las funciones: " + e.getMessage());
            e.printStackTrace();
        }
    }
        public void eliminarFuncion(String idFuncion) {
        Funcion funcion = buscarFuncion(idFuncion);
        if (funcion != null) {
            funciones.remove(funcion);
            guardarFunciones();
            System.out.println("Función eliminada exitosamente.");
        } else {
            System.out.println("Error: No se encontró la función con el ID especificado.");
        }
    }
}