/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cinemaseat.modelos;

/**
 *
 * @author abrahamcedeno
 */
import java.util.List;

public class Boleto {
    private String idFuncion;
    private List<String> asientos;
    private double subtotal; 

       public Boleto(String idFuncion, List<String> asientos, double subtotal) {
        this.idFuncion = idFuncion;
        this.asientos = asientos;
        this.subtotal = subtotal;
    }

    // Constructor sin subtotal, por defecto 0.0
    public Boleto(String idFuncion, List<String> asientos) {
        this(idFuncion, asientos, 0.0);
    }

    public String getIdFuncion() {
        return idFuncion;
    }

    public void setIdFuncion(String idFuncion) {
        this.idFuncion = idFuncion;
    }

    public List<String> getAsientos() {
        return asientos;
    }

    public void setAsientos(List<String> asientos) {
        this.asientos = asientos;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return "Boleto{" +
                "idFuncion='" + idFuncion + '\'' +
                ", asientos=" + asientos +
                ", subtotal=" + subtotal +
                '}';
    }
}