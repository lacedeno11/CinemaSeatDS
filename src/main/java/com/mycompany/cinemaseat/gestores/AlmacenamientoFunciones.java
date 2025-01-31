/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cinemaseat.gestores;

import com.mycompany.cinemaseat.modelos.Funcion;
import java.util.ArrayList;
import java.util.List;

public class AlmacenamientoFunciones {
    private List<Funcion> funciones;

    public AlmacenamientoFunciones() {
        this.funciones = new ArrayList<>();
    }

    public List<Funcion> obtenerFunciones() {
        return funciones;
    }

    public void guardarFunciones(List<Funcion> funcionesActualizadas) {
        this.funciones = new ArrayList<>(funcionesActualizadas);
    }

    public void agregarFuncion(Funcion funcion) {
        funciones.add(funcion);
    }
}