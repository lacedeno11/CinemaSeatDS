package com.mycompany.cinemaseat.modelos;

/**
 * Clase que encapsula los datos de usuario para reducir la cantidad de parámetros en los métodos.
 */
public class UsuarioDatos {
    private String nombre;
    private String email;
    private String password;

    public UsuarioDatos(String nombre, String email, String password) {
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

    public String getPassword() {
        return password;
    }
}
