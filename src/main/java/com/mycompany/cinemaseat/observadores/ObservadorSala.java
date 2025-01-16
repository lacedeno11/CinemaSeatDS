package com.mycompany.cinemaseat.observadores;

import com.mycompany.cinemaseat.gestores.GestorNotificaciones;
import com.mycompany.cinemaseat.modelos.Cliente;

import java.util.List;

public class ObservadorSala implements Observer {
    private GestorNotificaciones gestorNotificaciones;
    private List<Cliente> clientes;

    public ObservadorSala(GestorNotificaciones gestorNotificaciones, List<Cliente> clientes) {
        this.gestorNotificaciones = gestorNotificaciones;
        this.clientes = clientes;
    }

    @Override
    public void actualizar(String mensaje) {
        System.out.println("Notificación de la sala: " + mensaje);
        gestorNotificaciones.enviarNotificacion(clientes, mensaje);
    }
}