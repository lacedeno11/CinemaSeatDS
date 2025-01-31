import com.mycompany.cinemaseat.modelos.Boleto;
import com.mycompany.cinemaseat.modelos.Cliente;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Pruebas para Usuario (No instanciable directamente, pero usada en Cliente)
public class ClienteTest {
    @Test
    public void testClienteConstructor() {
        List<Boleto> boletos = new ArrayList<>();
        Cliente cliente = new Cliente("Juan", "juan@mail.com", "12345", boletos);
        assertEquals("Juan", cliente.getNombre());
        assertEquals("juan@mail.com", cliente.getEmail());
    }
}
