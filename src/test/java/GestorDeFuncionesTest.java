/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author abrahamcedeno
 */
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class GestorFuncionesTest {
    private GestorFunciones gestor;
    private static final String TEST_JSON_PATH = "src/test/resources/data/DBjson/funciones_test.json";

    @BeforeEach
    void setUp() {
        gestor = new GestorFunciones();
        gestor.setArchivoFunciones(TEST_JSON_PATH); // Requiere refactorización
    }

    @Test
    void cargarFunciones_ArchivoValido_ListaNoVacia() {
        List<Funcion> funciones = gestor.obtenerFunciones();
        assertFalse(funciones.isEmpty());
    }

    @Test
    void agregarFuncion_IDNuevo_FuncionAgregada() {
        Funcion nuevaFuncion = new Funcion("F100", "Sala 1", "18:00", "Activa");
        gestor.agregarFuncion(nuevaFuncion);
        assertNotNull(gestor.buscarFuncion("F100"));
    }

    @Test
    void modificarFuncion_CambiarHorario_HorarioActualizado() {
        gestor.modificarFuncion("F1", "19:30", null, false);
        Funcion funcion = gestor.buscarFuncion("F1");
        assertEquals("19:30", funcion.getHorario());
    }

    @Test
    void eliminarFuncion_IDExistente_FuncionEliminada() {
        gestor.eliminarFuncion("F1");
        assertNull(gestor.buscarFuncion("F1"));
    }
}