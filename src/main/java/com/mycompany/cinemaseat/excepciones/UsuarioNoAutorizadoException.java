package com.mycompany.cinemaseat.excepciones;

public class UsuarioNoAutorizadoException extends Exception {
    public UsuarioNoAutorizadoException(String mensaje) {
        super(mensaje);
    }
}