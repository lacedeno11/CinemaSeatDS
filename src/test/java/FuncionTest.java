import com.mycompany.cinemaseat.modelos.Funcion;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Pruebas para Funcion
public class FuncionTest {
    @Test
    public void testFuncionConstructor() {
        Set<String> asientos = new HashSet<>();
        Funcion funcion = new Funcion("Sala 1", "Pelicula 1", "18:00", "Activa", "Español", asientos);
        assertEquals("Sala 1", funcion.getSala());
        assertEquals("Pelicula 1", funcion.getPelicula());
        assertEquals("18:00", funcion.getHorario());
    }
}
