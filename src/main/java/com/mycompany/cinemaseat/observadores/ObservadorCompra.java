package com.mycompany.cinemaseat.observadores;

import com.mycompany.cinemaseat.gestores.GestorNotificaciones;
import com.mycompany.cinemaseat.modelos.Cliente;


public class ObservadorCompra implements Observer {
    private GestorNotificaciones gestorNotificaciones;
    private Cliente cliente;

    public ObservadorCompra(GestorNotificaciones gestorNotificaciones, Cliente cliente) {
        this.gestorNotificaciones = gestorNotificaciones;
        this.cliente = cliente;
    }

    @Override
    public void actualizar(String mensaje) {
        System.out.println("Notificación de compra: " + mensaje);
        gestorNotificaciones.enviarCorreoNotificacion(cliente, mensaje);
    }
}