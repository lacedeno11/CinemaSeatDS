package com.mycompany.cinemaseat.gestores;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.mycompany.cinemaseat.modelos.Boleto;
import com.mycompany.cinemaseat.modelos.Cliente;
import com.mycompany.cinemaseat.modelos.Usuario;
import com.mycompany.cinemaseat.modelos.Administrador;
import com.mycompany.cinemaseat.excepciones.UsuarioNoAutorizadoException;
import com.mycompany.cinemaseat.modelos.Funcion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Clase que gestiona las operaciones relacionadas con los usuarios.
 */
public class GestorUsuarios {
    private List<Usuario> usuarios;
    private static final String ARCHIVO_USUARIOS = "data/DBjson/usuarios.json";

    public GestorUsuarios() throws IOException {
        this.usuarios = cargarUsuarios();
    }

    /**
     * Carga los usuarios desde el archivo JSON.
     *
     * @return Lista de usuarios cargados.
     */
    private List<Usuario> cargarUsuarios() throws IOException {
        Path path = Paths.get(ARCHIVO_USUARIOS);

        if (!Files.exists(path)) {
            System.out.println("El archivo de usuarios no existe en la ruta: " + ARCHIVO_USUARIOS);
            return new ArrayList<>();
        }

        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            // Configurar Gson con el UsuarioAdapter personalizado
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Usuario.class, new UsuarioAdapter()) // Registro del TypeAdapter
                    .setPrettyPrinting()
                    .create();

            // Definir el tipo de la lista de usuarios
            Type tipoLista = new TypeToken<List<Usuario>>() {}.getType();

            // Deserializar el JSON en una lista de usuarios
            List<Usuario> usuariosCargados = gson.fromJson(reader, tipoLista);

            if (usuariosCargados != null) {
                System.out.println("Usuarios cargados exitosamente.");
                return usuariosCargados;
            } else {
                System.out.println("No se encontraron usuarios en el archivo JSON.");
                return new ArrayList<>();
            }
        } catch (JsonParseException e) {
            System.out.println("Error al deserializar el JSON de usuarios: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        } catch (IOException e) {
            System.out.println("Error al cargar los usuarios: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Guarda los usuarios actuales en el archivo JSON.
     */
    public void guardarUsuarios() {
        Path path = Paths.get(ARCHIVO_USUARIOS);

        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .create();
            gson.toJson(this.usuarios, writer);
            System.out.println("Usuarios guardados exitosamente en " + ARCHIVO_USUARIOS);
        } catch (IOException e) {
            System.out.println("Error al guardar los usuarios: " + e.getMessage());
            e.printStackTrace();
        }
    }



    /**
     * Busca un usuario por su email.
     *
     * @param email El email del usuario a buscar.
     * @return El usuario encontrado o null si no existe.
     */
    public Usuario buscarUsuario(String email) {
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equalsIgnoreCase(email)) {
                return usuario;
            }
        }
        return null;
    }

    /**
     * Registra un nuevo cliente.
     *
     * @param nombre   El nombre del cliente.
     * @param email    El email del cliente.
     * @param password La contraseña del cliente.
     */
    public void registrarCliente(String nombre, String email, String password) {
        if (buscarUsuario(email) != null) {
            System.out.println("Error: Ya existe un usuario con el mismo email.");
            return;
        }
        Cliente nuevoCliente = new Cliente(nombre, email, password, new ArrayList<>());
        usuarios.add(nuevoCliente);
        guardarUsuarios();
        System.out.println("Cuenta registrada exitosamente.");
    }

    /**
     * Elimina un usuario por su email.
     *
     * @param email El email del usuario a eliminar.
     */
    public void eliminarUsuario(String email) {
        Usuario usuario = buscarUsuario(email);
        if (usuario != null) {
            usuarios.remove(usuario);
            guardarUsuarios();
            System.out.println("Usuario eliminado exitosamente.");
        } else {
            System.out.println("Error: No se encontró el usuario con el email especificado.");
        }
    }

    /**
     * Inicia sesión con email y contraseña.
     *
     * @param email    El email del usuario.
     * @param password La contraseña del usuario.
     * @return El usuario autenticado.
     * @throws UsuarioNoAutorizadoException Si las credenciales son incorrectas.
     */
    public Usuario iniciarSesion(String email, String password) throws UsuarioNoAutorizadoException {
        Usuario usuario = buscarUsuario(email);
        if (usuario != null && usuario.verificarPassword(password)) {
            return usuario;
        } else {
            throw new UsuarioNoAutorizadoException("Credenciales incorrectas o usuario no registrado.");
        }
    }

    /**
     * Agrega un boleto a un usuario por su email.
     *
     * @param email  El email del usuario.
     * @param boleto El boleto a agregar.
     */
    public void agregarBoletoAUsuario(String email, Boleto boleto) {
        Usuario usuario = buscarUsuario(email);
        if (usuario == null) {
            System.out.println("Error: No se encontró el usuario con el email especificado.");
            return;
        }
        if (usuario instanceof Cliente) {
            Cliente cliente = (Cliente) usuario;
            cliente.agregarBoleto(boleto);
            guardarUsuarios();
            System.out.println("Boleto agregado exitosamente al usuario: " + cliente.getNombre());
        } else {
            System.out.println("Error: Solo los clientes pueden comprar boletos.");
        }
    }

    /**
     * Muestra todos los usuarios cargados.
     */
    public void mostrarUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
            return;
        }

        System.out.println("Usuarios Registrados:");
        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
        }
    }
    public List<Usuario> obtenerUsuarios() {
        if (this.usuarios == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(this.usuarios);
    }
    /**
     * Clase interna UsuarioAdapter para deserializar correctamente Usuario y sus subclases.
     */
    private static class UsuarioAdapter implements JsonDeserializer<Usuario> {
        @Override
        public Usuario deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            JsonElement tipoUsuarioElement = jsonObject.get("tipoUsuario");

            if (tipoUsuarioElement == null || tipoUsuarioElement.isJsonNull()) {
                throw new JsonParseException("Falta el campo 'tipoUsuario' en el objeto JSON: " + jsonObject.toString());
            }

            String tipoUsuario = tipoUsuarioElement.getAsString();

            switch (tipoUsuario) {
                case "Cliente":
                    return context.deserialize(jsonObject, Cliente.class);
                case "Administrador":
                    return context.deserialize(jsonObject, Administrador.class);
                default:
                    throw new JsonParseException("Tipo de usuario desconocido: " + tipoUsuario);
            }
        }
    }
}