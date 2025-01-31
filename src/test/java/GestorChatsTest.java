/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author abrahamcedeno
 */
import com.mycompany.cinemaseat.gestores.GestorChats;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

public class GestorChatsTest {
    private GestorChats gestor;
    private static final String TEST_JSON_PATH = "equivocado :b";
    private static final String TEST_JSON_CORRECT_PATH = "data/DBjson/chats.json";

    @BeforeEach
    void setUp() {
        gestor = new GestorChats();
        gestor.setArchivoChats(TEST_JSON_PATH); 
    }

    // ---------------------- cargarChats() ----------------------
    @Test
    void cargarChats_ArchivoValido_Devuelve2Chats() {
        gestor.setArchivoChats(TEST_JSON_CORRECT_PATH);
        List<GestorChats.Chat> chats = gestor.cargarChats();
        assertEquals(2, chats.size());
    }

    @Test
    void cargarChats_ArchivoInexistente_ListaVacia() {
        gestor.setArchivoChats(TEST_JSON_PATH);
        assertTrue(gestor.cargarChats().isEmpty());
    }

    // ---------------------- obtenerChatsSinRespuesta() ----------------------
    @Test
    void obtenerChatsSinRespuesta_Hay1SinRespuesta_ListaTamano1() {
        // Agregar chat sin respuesta
        gestor.agregarChat("test@ejemplo.com", "Mensaje", "");
        assertEquals(1, gestor.obtenerChatsSinRespuesta().size());
    }

    @Test
    void obtenerChatsSinRespuesta_TodosRespondidos_ListaVacia() {
        gestor.getChats().forEach(chat -> chat.respuestaAdministrador = "respondido");
        assertTrue(gestor.obtenerChatsSinRespuesta().isEmpty());
    }

    // ---------------------- responderChatParaAdmin() ----------------------
    @Test
    void responderChatParaAdmin_ClienteExistente_ActualizaRespuesta() {
        List<GestorChats.Chat> sinRespuesta = gestor.obtenerChatsSinRespuesta();
        gestor.responderChatParaAdmin("juan.perez@example.com", "Nueva respuesta", sinRespuesta);
        String respuesta = gestor.getChats().get(0).respuestaAdministrador;
        assertEquals("Nueva respuesta", respuesta);
    }

    @Test
    void responderChatParaAdmin_ClienteInexistente_MensajeError() {
        List<GestorChats.Chat> sinRespuesta = gestor.obtenerChatsSinRespuesta();
        gestor.responderChatParaAdmin("inexistente@ejemplo.com", "...", sinRespuesta);
        // Verificar mensaje de error en logs
    }
}