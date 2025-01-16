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
import com.google.gson.reflect.TypeToken;
import com.mycompany.cinemaseat.modelos.Funcion;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GestorFunciones {
    private List<Funcion> funciones;
    private static final String ARCHIVO_FUNCIONES = "DBjson/funciones.json";

    public GestorFunciones() {
        this.funciones = cargarFunciones();
    }

    private List<Funcion> cargarFunciones() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(ARCHIVO_FUNCIONES);

        if (inputStream == null) {
            System.out.println("Error: No se pudo encontrar el archivo de funciones en la ruta: " + ARCHIVO_FUNCIONES);
            return new ArrayList<>();
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            Gson gson = new Gson();
            Type tipoLista = new TypeToken<List<Funcion>>() {}.getType();
            return gson.fromJson(reader, tipoLista);
        } catch (Exception e) {
            System.out.println("Error al cargar las funciones: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Funcion> obtenerFunciones() {
        return funciones;
    }

    public Funcion buscarFuncionPorSala(String sala) {
        for (Funcion funcion : funciones) {
            if (funcion.getSala().equalsIgnoreCase(sala)) {
                return funcion;
            }
        }
        return null;
    }
}