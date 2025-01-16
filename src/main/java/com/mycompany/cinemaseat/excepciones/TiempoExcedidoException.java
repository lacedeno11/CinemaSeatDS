package com.mycompany.cinemaseat.excepciones;

public class TiempoExcedidoException extends Exception {
    public TiempoExcedidoException(String mensaje) {
        super(mensaje);
    }
}