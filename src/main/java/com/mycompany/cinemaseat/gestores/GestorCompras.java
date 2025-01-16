package com.mycompany.cinemaseat.gestores;

import com.mycompany.cinemaseat.modelos.Asiento;
import com.mycompany.cinemaseat.modelos.Sala;
import com.mycompany.cinemaseat.excepciones.AsientoNoDisponibleException;
import com.mycompany.cinemaseat.excepciones.TiempoExcedidoException;

import java.util.Timer;
import java.util.TimerTask;

public class GestorCompras {
    private static final long TIEMPO_LIMITE_COMPRA_MS = 300000; // 5 minutos en milisegundos
    private boolean tiempoExcedido = false;

    public void iniciarCompra(Sala sala, Asiento asiento) throws AsientoNoDisponibleException {
        if (!asiento.estaDisponible()) {
            throw new AsientoNoDisponibleException("El asiento " + asiento.getUbicacion() + " no está disponible.");
        }

        System.out.println("Asiento " + asiento.getUbicacion() + " seleccionado. Tienes 5 minutos para completar la compra.");
        iniciarTemporizador(asiento);
    }

    private void iniciarTemporizador(Asiento asiento) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!tiempoExcedido) {
                    asiento.cambiarEstado("DISPONIBLE");
                    tiempoExcedido = true;
                    System.out.println("Tiempo límite excedido. El asiento ha sido liberado.");
                }
            }
        }, TIEMPO_LIMITE_COMPRA_MS);
    }

    public void completarCompra(Asiento asiento) throws TiempoExcedidoException {
        if (tiempoExcedido) {
            throw new TiempoExcedidoException("No se puede completar la compra. Tiempo excedido.");
        }

        asiento.cambiarEstado("RESERVADO");
        System.out.println("Compra completada. Asiento reservado exitosamente.");
    }
}