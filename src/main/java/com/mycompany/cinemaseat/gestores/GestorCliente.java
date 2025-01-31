package com.mycompany.cinemaseat.gestores;

import com.mycompany.cinemaseat.modelos.Boleto;
import com.mycompany.cinemaseat.modelos.Cliente;
import com.mycompany.cinemaseat.modelos.Usuario;
import com.mycompany.cinemaseat.modelos.UsuarioDatos;

import java.util.List;

public class GestorCliente {
    private List<Usuario> usuarios;
    private GestorAutenticacion gestorAutenticacion;

    public GestorCliente(List<Usuario> usuarios, GestorAutenticacion gestorAutenticacion) {
        this.usuarios = usuarios;
        this.gestorAutenticacion = gestorAutenticacion;
    }

    /**
     * Registra un nuevo cliente utilizando un objeto `UsuarioDatos`.
     *
     * @param usuarioDatos Objeto que encapsula los datos del usuario.
     */
    public void registrarCliente(UsuarioDatos usuarioDatos) {
        if (gestorAutenticacion.buscarUsuario(usuarioDatos.getEmail()) != null) {
            System.out.println("Error: Ya existe un usuario con el mismo email.");
            return;
        }
        Cliente nuevoCliente = new Cliente(usuarioDatos.getNombre(), usuarioDatos.getEmail(), usuarioDatos.getPassword(), List.of());
        usuarios.add(nuevoCliente);
        System.out.println("Cuenta registrada exitosamente.");
    }

    /**
     * Elimina un usuario por su email.
     *
     * @param email El email del usuario a eliminar.
     */
    public void eliminarUsuario(String email) {
        Usuario usuario = gestorAutenticacion.buscarUsuario(email);
        if (usuario != null) {
            usuarios.remove(usuario);
            System.out.println("Usuario eliminado exitosamente.");
        } else {
            System.out.println("Error: No se encontró el usuario con el email especificado.");
        }
    }

    /**
     * Agrega un boleto a un usuario por su email.
     *
     * @param email  El email del usuario.
     * @param boleto El boleto a agregar.
     */
    public void agregarBoletoAUsuario(String email, Boleto boleto) {
        Usuario usuario = gestorAutenticacion.buscarUsuario(email);
        if (usuario == null) {
            System.out.println("Error: No se encontró el usuario con el email especificado.");
            return;
        }
        if (usuario instanceof Cliente) {
            Cliente cliente = (Cliente) usuario;
            cliente.agregarBoleto(boleto);
            System.out.println("Boleto agregado exitosamente al usuario: " + cliente.getNombre());
        } else {
            System.out.println("Error: Solo los clientes pueden comprar boletos.");
        }
    }
}
