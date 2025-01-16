package com.mycompany.cinemaseat.tipos_asientos;

import com.mycompany.cinemaseat.modelos.Asiento;

public class AsientoEstandar implements Asiento {
    private String ubicacion;
    private String estado;
    private final double precio = 100.00;  // Precio para estándar
    private final String tipo = "Estándar";

    public AsientoEstandar(String ubicacion) {
        this.ubicacion = ubicacion;
        this.estado = "DISPONIBLE";  // Estado inicial
    }

    @Override
    public String getDescripcion() {
        return "Asiento estándar de cine";
    }

    @Override
    public double getPrecio() {
        return precio;
    }

    @Override
    public boolean estaDisponible() {
        return estado.equalsIgnoreCase("DISPONIBLE");
    }

    @Override
    public void cambiarEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
    }

    @Override
    public String getUbicacion() {
        return ubicacion;
    }

    @Override
    public String getTipoAsiento() {
        return tipo;
    }
}