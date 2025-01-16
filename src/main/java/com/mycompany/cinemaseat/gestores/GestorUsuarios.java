package com.mycompany.cinemaseat.gestores;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.mycompany.cinemaseat.modelos.Cliente;
import com.mycompany.cinemaseat.modelos.Usuario;
import com.mycompany.cinemaseat.modelos.Administrador;
import com.mycompany.cinemaseat.excepciones.UsuarioNoAutorizadoException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

public class GestorUsuarios {
    private HashMap<String, Usuario> usuariosRegistrados;
    private static final String ARCHIVO_USUARIOS = "DBjson/usuarios.json";

    public GestorUsuarios() {
        this.usuariosRegistrados = new HashMap<>();
        cargarUsuarios();
    }

    public void cargarUsuarios() {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(ARCHIVO_USUARIOS);

        if (inputStream == null) {
            System.out.println("Error: No se pudo encontrar el archivo de usuarios en la ruta: " + ARCHIVO_USUARIOS);
            return;
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            // Configurar Gson con el UsuarioAdapter
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Usuario.class, new UsuarioAdapter()) // Registro del TypeAdapter
                    .create();

            Type tipoLista = new TypeToken<List<Usuario>>() {}.getType();
            List<Usuario> usuarios = gson.fromJson(reader, tipoLista);

            // Agregar usuarios al HashMap
            for (Usuario usuario : usuarios) {
                usuariosRegistrados.put(usuario.getEmail(), usuario);
            }

            System.out.println("Usuarios cargados exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al cargar los usuarios: " + e.getMessage());
        }
    }

    public void registrarCliente(String nombre, String email, String password) {
        if (usuariosRegistrados.containsKey(email)) {
            System.out.println("Este correo ya está registrado.");
        } else {
            Cliente nuevoCliente = new Cliente(nombre, email, password);
            usuariosRegistrados.put(email, nuevoCliente);
            System.out.println("Cuenta registrada exitosamente.");
        }
    }

    public Usuario iniciarSesion(String email, String password) throws UsuarioNoAutorizadoException {
        Usuario usuario = usuariosRegistrados.get(email);
        if (usuario != null && usuario.verificarPassword(password)) {
            return usuario;
        } else {
            throw new UsuarioNoAutorizadoException("Credenciales incorrectas o usuario no registrado.");
        }
    }

    /**
     * Clase interna UsuarioAdapter para deserializar correctamente Usuario.
     */
    private static class UsuarioAdapter implements JsonDeserializer<Usuario> {
        @Override
        public Usuario deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            JsonObject jsonObject = json.getAsJsonObject();
            String tipoUsuario = jsonObject.get("tipoUsuario").getAsString(); // Campo "tipoUsuario"

            // Deserializar según el tipo de usuario
            switch (tipoUsuario) {
                case "Cliente":
                    return context.deserialize(json, Cliente.class);
                case "Administrador":
                    return context.deserialize(json, Administrador.class);
                default:
                    throw new JsonParseException("Tipo de usuario desconocido: " + tipoUsuario);
            }
        }
    }
}