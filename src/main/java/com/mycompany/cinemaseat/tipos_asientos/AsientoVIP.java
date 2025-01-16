package com.mycompany.cinemaseat.tipos_asientos;

import com.mycompany.cinemaseat.modelos.Asiento;

public class AsientoVIP implements Asiento {
    private String ubicacion;
    private String estado;
    private final double precio = 200.00;  // Precio para VIP
    private final String tipo = "VIP";

    public AsientoVIP(String ubicacion) {
        this.ubicacion = ubicacion;
        this.estado = "DISPONIBLE";  // Estado inicial
    }

    @Override
    public String getDescripcion() {
        return "Asiento VIP con mayor espacio y comodidad";
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