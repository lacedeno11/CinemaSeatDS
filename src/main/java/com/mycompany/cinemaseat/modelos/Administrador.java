package com.mycompany.cinemaseat.modelos;

public class Administrador extends Usuario {
    private static final String ACCESO_ESPECIAL = "ADMIN";
 
     
    public Administrador(String nombre, String email, String password) {
        super(nombre, email, password, "Administrador");
    }

    @Override
    public String getTipoUsuario() {
        return "Administrador";
    }

    public boolean tieneAccesoEspecial(String claveAcceso) {
        return ACCESO_ESPECIAL.equals(claveAcceso);
    }
}