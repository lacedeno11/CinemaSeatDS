package com.mycompany.cinemaseat.modelos;

public interface Asiento {
    String getDescripcion();  // Devuelve una descripción del asiento
    double getPrecio();// Precio del asiento
    boolean estaDisponible(); // Comprueba si está disponible
    void cambiarEstado(String nuevoEstado);  // Cambia el estado del asiento (disponible, reservado, en proceso)
    String getUbicacion();    // Devuelve la ubicación del asiento (ej. A1, B3)
    String getTipoAsiento();  // Devuelve el tipo del asiento (ej. 4D, VIP, Estándar)
}