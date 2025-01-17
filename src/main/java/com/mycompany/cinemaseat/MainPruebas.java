/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.cinemaseat;

import com.mycompany.cinemaseat.excepciones.UsuarioNoAutorizadoException;
import com.mycompany.cinemaseat.gestores.GestorSalas;
import com.mycompany.cinemaseat.gestores.GestorChats;
import com.mycompany.cinemaseat.gestores.GestorFunciones;
import com.mycompany.cinemaseat.gestores.GestorUsuarios;
import com.mycompany.cinemaseat.modelos.Boleto;
import com.mycompany.cinemaseat.modelos.Cliente;
import com.mycompany.cinemaseat.modelos.Usuario;
import com.mycompany.cinemaseat.visualizacion.MenuAdministrador;
import java.io.IOException;
import java.util.Arrays;

import java.util.List;
import java.util.Scanner;

public class MainPruebas {
    public static void main(String[] args) throws IOException {
        GestorFunciones gestorFunciones = new GestorFunciones();
        GestorSalas gestorSalas = new GestorSalas();
        GestorUsuarios gestorUsuarios = new GestorUsuarios();

        MenuAdministrador menuAdmin = new MenuAdministrador(gestorFunciones, gestorSalas, gestorUsuarios);
        menuAdmin.mostrarMenu();
    }
 }
