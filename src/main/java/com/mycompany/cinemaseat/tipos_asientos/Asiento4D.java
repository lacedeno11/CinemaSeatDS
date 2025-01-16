package com.mycompany.cinemaseat.tipos_asientos;

import com.mycompany.cinemaseat.modelos.Asiento;

public class Asiento4D implements Asiento {
    private String ubicacion;
    private String estado;
    private final double precio = 250.00;  // Precio para 4D
    private final String tipo = "4D";

    public Asiento4D(String ubicacion) {
        this.ubicacion = ubicacion;
        this.estado = "DISPONIBLE";  // Estado inicial
    }

    @Override
    public String getDescripcion() {
        return "Asiento 4D con movimiento y vibración";
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