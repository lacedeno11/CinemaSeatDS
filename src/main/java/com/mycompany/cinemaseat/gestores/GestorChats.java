/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cinemaseat.gestores;

/**
 *
 * @author abrahamcedeno
 */


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GestorChats {
    private String archivoChats = "data/DBjson/chats.json";
    private List<Chat> chats;

    public static class Chat {
        public String cliente;  // Cambiar a public
        public String mensajeCliente;  // Cambiar a public
        public String respuestaAdministrador;  // Cambiar a public

        public Chat(String cliente, String mensajeCliente, String respuestaAdministrador) {
            this.cliente = cliente;
            this.mensajeCliente = mensajeCliente;
            this.respuestaAdministrador = respuestaAdministrador;
        }
    }

    public GestorChats() {
        this.chats = cargarChats();
    }
    public void setArchivoChats(String ruta) {
        this.archivoChats = ruta;
    }
    public List<Chat> cargarChats() {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(archivoChats);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            Gson gson = new Gson();
            Type tipoLista = new TypeToken<List<Chat>>() {}.getType();
            return gson.fromJson(reader, tipoLista);
        } catch (Exception e) {
            System.out.println("Error al cargar los chats: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void agregarChat(String cliente, String mensajeCliente, String respuestaAdministrador) {
        chats.add(new Chat(cliente, mensajeCliente, respuestaAdministrador));
        guardarChats();
    }

    public void mostrarChats() {
        System.out.println("\n--- Chats de Clientes ---");
        for (Chat chat : chats) {
            System.out.println("Cliente: " + chat.cliente);  // Acceso público
            System.out.println("Mensaje: " + chat.mensajeCliente);  // Acceso público
            System.out.println("Respuesta: " + chat.respuestaAdministrador);  // Acceso público
            System.out.println("-------------------------");
        }
    }

    public void guardarChats() {
        Gson gson = new Gson();
        String json = gson.toJson(chats);

        try (java.io.FileWriter writer = new java.io.FileWriter("src/main/resources/DBjson/chats.json")) {
            writer.write(json);
            System.out.println("Chats guardados exitosamente.");
        } catch (java.io.IOException e) {
            System.out.println("Error al guardar los chats: " + e.getMessage());
        }
    }
    public List<Chat> obtenerChatsSinRespuesta(){
        List<Chat> chatsSinRespuesta = new ArrayList<>();
        for (Chat chat : chats) {
            if (chat.respuestaAdministrador == null || chat.respuestaAdministrador.isEmpty()) {
                chatsSinRespuesta.add(chat);
            }
        }
        return chatsSinRespuesta;
    }

    public void responderChatParaAdmin(String cliente, String respuestaAdministrador,List<Chat> chatsSinRespuestas ) {
        for (Chat chat : chatsSinRespuestas) {
            if (chat.cliente.equals(cliente) && (chat.respuestaAdministrador == null || chat.respuestaAdministrador.isEmpty())) {
                chat.respuestaAdministrador = respuestaAdministrador;
                guardarChats();
                System.out.println("Respuesta enviada al cliente: " + cliente);
                return;
            }
        }
        System.out.println("No se encontró un chat sin respuesta para el cliente: " + cliente);
    }

    public void iniciarChat(String cliente, String mensajeCliente) {
        chats.add(new Chat(cliente, mensajeCliente, ""));
        guardarChats();
        System.out.println("Chat iniciado por el cliente: " + cliente);
    }
    public List<Chat> getChats() {
    return this.chats;
}
}