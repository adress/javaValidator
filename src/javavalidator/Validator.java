/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javavalidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 *
 * @author Andres
 */
public class Validator {

    List<String> errores;

    public Validator() {
        errores = new ArrayList<>();
    }

    public void clearErrors() {
        errores.clear();
    }

    public int getCountErrors() {
        return errores.size();
    }

    public List<String> getErrors() {
        return errores;
    }
    
    public String getErrorsMessage(){
      return errores.stream().collect(Collectors.joining( "\n" ));
    }

    public String texto(String texto, String nombre, String reglas) {
        return validador(texto, nombre, reglas);
    }

    public int entero(String numero, String nombre, String reglas) {
        reglas += "|integer";
        String respuesta = validador(numero, nombre, reglas);
        return respuesta.equals("") ? 0 : Integer.parseInt(respuesta);
    }

    public double doble(String numero, String nombre, String reglas) {
        reglas += "|double";
        String respuesta = validador(numero, nombre, reglas);
        return respuesta.equals("") ? 0 : Double.parseDouble(respuesta);
    }

    public String validador(String texto, String nombre, String reglas) {

        boolean isNumeric = false;
        double textoLength = texto.length();

        if (textoLength > 0) {
            if (reglas.contains("integer") || reglas.contains("double")) {
                try {
                    textoLength = Double.parseDouble(texto);
                    isNumeric = true;
                } catch (NumberFormatException | NullPointerException e) {
                    errores.add("El campo " + nombre + " debe ser numerico");
                }
            }
        }

        String[] parametros = new String[3];
        List<String> rules = Arrays.asList(reglas.split("\\s*\\|\\s*"));
        //System.out.println(nombre + ": " + rules); //print rules
        for (String regla : rules) {
            //parte los parametros de las reglas {nombreregla, param1, param2}
            parametros = regla.split("\\s*:\\s*");
            //System.out.println(regla + ": " + parametros[0]);
            switch (parametros[0]) {
                case "min":
                    if (textoLength < Integer.parseInt(parametros[1])) {
                        if (isNumeric) {
                            errores.add("El campo " + nombre + " debe ser mayor o igual a " + parametros[1]);
                        } else {
                            errores.add("El campo " + nombre + " debe tener almenos " + parametros[1] + " caracteres");
                        }
                    }
                    break;
                case "max":
                    if (textoLength > Integer.parseInt(parametros[1])) {
                        if (isNumeric) {
                            errores.add("El campo " + nombre + " debe ser menor o igual a " + parametros[1]);
                        } else {
                            errores.add("El campo " + nombre + " debe tener maximo " + parametros[1] + " caracteres");
                        }
                    }
                    break;
                case "integer":
                    if (textoLength > 0) {
                        try {
                            Double.parseDouble(texto);
                        } catch (NumberFormatException | NullPointerException e) {
                            errores.add("El campo " + nombre + " debe ser numerico");
                        }
                    }
                    break;
                case "email":
                    if (textoLength > 0) {
                        if (!validarEmail(texto)) {
                            errores.add("El campo " + nombre + " no es un email valido");
                        }
                    }
                    break;
                case "requeried":
                    if (texto.length() == 0) {
                        errores.add("El campo " + nombre + " es requerido");
                    }
                    break;
                default:
                    errores.add("Regla " + parametros[0] + " desconocida");
            }

        }
        return texto;
    }

    public boolean validarEmail(String email) {
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(email);
        return mather.find();
    }

}
