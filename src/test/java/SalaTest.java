
import com.mycompany.cinemaseat.modelos.*;
import com.mycompany.cinemaseat.tipos_asientos.AsientoEstandar;
import com.mycompany.cinemaseat.utils.ValidadorCorreo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

// Pruebas para Sala
public class SalaTest {
    @Test
    public void testSalaConstructor() {
        Sala sala = new Sala("Sala 1",2,  2,true, HashMap.newHashMap(10) );
        assertEquals("Sala 1", sala.getNombreSala());
        assertEquals(5, sala.getFilas());
        assertEquals(5, sala.getColumnas());
    }
}

