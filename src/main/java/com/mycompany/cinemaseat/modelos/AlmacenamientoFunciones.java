package com.mycompany.cinemaseat.modelos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mycompany.cinemaseat.modelos.Funcion;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class AlmacenamientoFunciones {
    private static final String ARCHIVO_FUNCIONES = "data/DBjson/funciones.json";

    public List<Funcion> cargarFunciones() {
        Path path = Paths.get(ARCHIVO_FUNCIONES);

        if (!Files.exists(path)) {
            System.out.println("Error: No se pudo encontrar el archivo de funciones en la ruta: " + ARCHIVO_FUNCIONES);
            return new ArrayList<>();
        }

        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            Gson gson = new Gson();
            Type tipoLista = new TypeToken<List<Funcion>>() {}.getType();
            List<Funcion> funcionesCargadas = gson.fromJson(reader, tipoLista);
            return (funcionesCargadas != null) ? funcionesCargadas : new ArrayList<>();
        } catch (Exception e) {
            System.out.println("Error al cargar las funciones: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void guardarFunciones(List<Funcion> funciones) {
        Path path = Paths.get(ARCHIVO_FUNCIONES);
        Path parentDir = path.getParent();

        try {
            if (parentDir != null && !Files.exists(parentDir)) {
                Files.createDirectories(parentDir);
            }

            try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                gson.toJson(funciones, writer);
            }
            System.out.println("Funciones guardadas exitosamente en " + ARCHIVO_FUNCIONES);
        } catch (IOException e) {
            System.out.println("Error al guardar las funciones: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
