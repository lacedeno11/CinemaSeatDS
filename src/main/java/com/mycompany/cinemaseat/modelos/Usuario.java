package com.mycompany.cinemaseat.modelos;

public abstract class Usuario {
    protected String nombre;
    protected String email;
    protected String password;

    public Usuario(String nombre, String email, String password) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
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

    public abstract String getTipoUsuario();  // Método abstracto que será implementado por `Cliente` y `Administrador`
}