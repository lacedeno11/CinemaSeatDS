package com.mycompany.cinemaseat.gestores;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycompany.cinemaseat.modelos.Sala;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GestorSalas {
    private List<Sala> salas;
    private static final String ARCHIVO_SALAS = "DBjson/salas.json";

    public GestorSalas() {
        this.salas = cargarSalas();
    }

    public void agregarSala(Sala sala) {
        salas.add(sala);
        guardarSalas();
        System.out.println("Sala agregada exitosamente.");
    }

    public Sala buscarSalaPorNombre(String nombreSala) {
        for (Sala sala : salas) {
            if (sala.getNombreSala().equalsIgnoreCase(nombreSala)) {
                return sala;
            }
        }
        return null;
    }

    public List<Sala> obtenerSalasDisponibles() {
        List<Sala> salasDisponibles = new ArrayList<>();
        for (Sala sala : salas) {
            if (sala.isDisponible()) {
                salasDisponibles.add(sala);
            }
        }
        return salasDisponibles;
    }

    public List<Sala> obtenerSalas() {
        return salas;
    }

    private List<Sala> cargarSalas() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(ARCHIVO_SALAS);

        if (inputStream == null) {
            System.out.println("Error: No se pudo encontrar el archivo de salas en la ruta: " + ARCHIVO_SALAS);
            return new ArrayList<>();
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            Gson gson = new Gson();
            Type tipoLista = new TypeToken<List<Sala>>() {}.getType();
            return gson.fromJson(reader, tipoLista);
        } catch (Exception e) {
            System.out.println("Error al cargar las salas: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    private void guardarSalas() {
        System.out.println("Guardado de salas no implementado aún.");
    }
}