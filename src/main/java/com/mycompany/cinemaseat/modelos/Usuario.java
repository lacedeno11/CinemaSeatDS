package com.mycompany.cinemaseat.modelos;

public abstract class Usuario {
    protected String nombre;
    protected String email;
    protected String password;
    protected String tipoUsuario; // Nuevo campo

    public Usuario(String nombre, String email, String password, String tipoUsuario) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.tipoUsuario = tipoUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public boolean verificarPassword(String passwordIngresada) {
        return this.password.equals(passwordIngresada);
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }
}