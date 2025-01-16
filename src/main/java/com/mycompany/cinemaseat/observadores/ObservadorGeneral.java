/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cinemaseat.observadores;

import com.mycompany.cinemaseat.gestores.GestorNotificaciones;
import com.mycompany.cinemaseat.modelos.Cliente;
import java.util.List;

/**
 *
 * @author abrahamcedeno
 */
public class ObservadorGeneral implements Observer {
    private GestorNotificaciones gestorNotificaciones;
    private List<Cliente> clientes;

    public ObservadorGeneral(GestorNotificaciones gestorNotificaciones, List<Cliente> clientes) {
        this.gestorNotificaciones = gestorNotificaciones;
        this.clientes = clientes;
    }

    @Override
    public void actualizar(String mensaje) {
        System.out.println("Notificación general: " + mensaje);
        gestorNotificaciones.enviarNotificacion(clientes, mensaje);
    }
}