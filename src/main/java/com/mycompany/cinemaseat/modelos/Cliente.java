package com.mycompany.cinemaseat.modelos;

public class Cliente extends Usuario {
    private boolean notificacionesActivadas;

    public Cliente(String nombre, String email, String password) {
        super(nombre, email, password);
        this.notificacionesActivadas = true;  // Por defecto, las notificaciones están activadas
    }

    public boolean isNotificacionesActivadas() {
        return notificacionesActivadas;
    }

    public void activarNotificaciones() {
        this.notificacionesActivadas = true;
    }

    public void desactivarNotificaciones() {
        this.notificacionesActivadas = false;
    }

    @Override
    public String getTipoUsuario() {
        return "Cliente";
    }
}