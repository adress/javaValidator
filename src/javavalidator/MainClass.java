/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javavalidator;

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
        Validator validator = new Validator();
        persona.setNombre(validator.texto("jaim", "Nombre", "min:5|max:10"));
        persona.setApellido(validator.texto("12345", "Apellido", "max:5|requeried"));
        persona.setEdad(validator.entero("32", "Edad", "integer|max:30"));
        persona.setEmail(validator.texto("andres@123.co", "email", "requeried|email"));
        if (validator.getCountErrors() > 0) {
            System.out.println(validator.getErrorsMessage());
        } else {
            persona.guardar();
        }
    }
}
