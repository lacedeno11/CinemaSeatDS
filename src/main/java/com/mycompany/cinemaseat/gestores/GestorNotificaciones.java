package com.mycompany.cinemaseat.gestores;

import java.util.List;
import com.mycompany.cinemaseat.modelos.Cliente;

public class GestorNotificaciones {

    public void enviarCorreoNotificacion(Cliente cliente, String mensaje) {
        System.out.println("Enviando correo a: " + cliente.getEmail());
        System.out.println("Mensaje: " + mensaje);
    }

    public void enviarNotificacion(List<Cliente> clientes, String mensaje) {
        for (Cliente cliente : clientes) {
            if (cliente.isNotificacionesActivadas()) {
                enviarCorreoNotificacion(cliente, mensaje);
            }
        }
    }
}