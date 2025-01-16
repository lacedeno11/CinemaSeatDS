package com.mycompany.cinemaseat.excepciones;

public class AsientoNoDisponibleException extends Exception {
    public AsientoNoDisponibleException(String mensaje) {
        super(mensaje);
    }
}