package com.mycompany.cinemaseat.utils;


public final class ValidadorCorreo{
    public static boolean validarCorreo(String correo){
        return correo.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$") 
        && correo!=null 
        && !correo.isEmpty();
    }
}