package com.mycompany.cinemaseat.modelos;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario {
    private List<Boleto> boletosComprados;

    /**
     * Constructor completo para Cliente que acepta boletosComprados.
     *
     * @param nombre            El nombre del cliente.
     * @param email             El correo electrónico del cliente.
     * @param password          La contraseña del cliente.
     * @param boletosComprados  La lista de boletos comprados por el cliente.
     */
    public Cliente(String nombre, String email, String password, List<Boleto> boletosComprados) {
        super(nombre, email, password, "Cliente"); // Establece el tipoUsuario como "Cliente"
        this.boletosComprados = boletosComprados != null ? boletosComprados : new ArrayList<>();
    }

    /**
     * Constructor para Cliente sin boletosComprados (lista vacía).
     *
     * @param nombre   El nombre del cliente.
     * @param email    El correo electrónico del cliente.
     * @param password La contraseña del cliente.
     */
    public Cliente(String nombre, String email, String password) {
        this(nombre, email, password, new ArrayList<>());
    }

    // Getters y Setters

    public List<Boleto> getBoletosComprados() {
        return boletosComprados;
    }

    public void setBoletosComprados(List<Boleto> boletosComprados) {
        this.boletosComprados = boletosComprados;
    }

    public void agregarBoleto(Boleto boleto) {
        this.boletosComprados.add(boleto);
    }

    public void eliminarBoleto(Boleto boleto) {
        this.boletosComprados.remove(boleto);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "tipoUsuario='" + tipoUsuario + '\'' +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", boletosComprados=" + boletosComprados +
                '}';
    }

    public boolean isNotificacionesActivadas() {
       return true;
    }
}