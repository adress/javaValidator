/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javavalidator;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andres
 */
public class MainClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Persona persona = new Persona();
        try {
            persona.setNombre(Validator.texto("Jaime", "Nombre", "min:5|requeried|max:10"));
            persona.setApellido(Validator.texto("12345", "Apellido", "max:10|requeried"));
            persona.setEdad(Integer.valueOf(Validator.texto("22", "Edad", "requeried|integer")));
            persona.guardar();
        } catch (Exception ex) {
            System.err.println("ERROR");
            System.err.println(ex.getMessage());
            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
