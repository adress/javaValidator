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
public class Person {

    private String nombre;
    private String apellido;
    private int edad;
    private String email;
    private double estatura;

    public void setEstatura(double estatura) {
        this.estatura = estatura;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void save() {
        System.out.println("Persona( \n"
                + "\tNombre:" + nombre + "\n"
                + "\tApellido:" + apellido + "\n"
                + "\tEdad:" + edad + "\n"
                + "\tEmail:" + email + "\n"
                + "\tEstatura:" + estatura + "\n"
                + ")");
    }

}
