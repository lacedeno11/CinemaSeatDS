package com.mycompany.cinemaseat.gestores;

import com.mycompany.cinemaseat.modelos.Usuario;
import com.mycompany.cinemaseat.excepciones.UsuarioNoAutorizadoException;

import java.util.List;

public class GestorAutenticacion {
    private List<Usuario> usuarios;

    public GestorAutenticacion(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }


    public Usuario buscarUsuario(String email) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equalsIgnoreCase(email)) {
                return usuario;
            }
        }
        return null;
    }


    public Usuario iniciarSesion(String email, String password) throws UsuarioNoAutorizadoException {
        Usuario usuario = buscarUsuario(email);
        if (usuario != null && usuario.verificarPassword(password)) {
            return usuario;
        } else {
            throw new UsuarioNoAutorizadoException("Credenciales incorrectas o usuario no registrado.");
        }
    }
}
