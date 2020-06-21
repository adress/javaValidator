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
        Person person = new Person();
        Validator validator = new Validator();
        person.setNombre(validator.text("jaime", "Nombre", "min:5|max:10"));
        person.setApellido(validator.text("12345", "Apellido", "max:5|requeried"));
        person.setEdad(validator.integer("30jc", "Edad", "integer|max:30"));
        person.setEmail(validator.text("andres@123.co", "email", "requeried|email"));
        person.setEstatura(validator.doble("1.34", "estatura", "requeried|double"));
        if (validator.getCountErrors() > 0) {
            System.out.println(validator.getErrorsMessage());
        } else {
            person.save();
        }
    }
}
