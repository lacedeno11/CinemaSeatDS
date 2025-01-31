import com.mycompany.cinemaseat.modelos.Boleto;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Pruebas para Boleto
public class BoletoTest {
    @Test
    public void testBoletoConstructor() {
        List<String> asientos = Arrays.asList("A1", "A2");
        Boleto boleto = new Boleto("FUNC123", asientos, 50.0);
        assertEquals("FUNC123", boleto.getIdFuncion());
        assertEquals(50.0, boleto.getSubtotal());
    }
}
