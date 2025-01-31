import com.mycompany.cinemaseat.modelos.Asiento;
import com.mycompany.cinemaseat.tipos_asientos.AsientoEstandar;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Pruebas para Asiento
public class AsientoTest {
    @Test
    public void testAsientoDisponible() {
        Asiento asiento = new AsientoEstandar("A1");
        assertTrue(asiento.estaDisponible());
        asiento.cambiarEstado("Reservado");
        assertFalse(asiento.estaDisponible());
    }
}
