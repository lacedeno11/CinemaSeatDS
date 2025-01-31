import com.mycompany.cinemaseat.utils.ValidadorCorreo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Pruebas para ValidadorCorreo
public class ValidadorCorreoTest {
    @Test
    public void testValidarCorreo() {
        assertTrue(ValidadorCorreo.validarCorreo("usuario@mail.com"));
        assertFalse(ValidadorCorreo.validarCorreo("usuario.com"));
    }
}
