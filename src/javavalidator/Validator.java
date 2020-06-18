/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javavalidator;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Andres
 */
public class Validator {

    /**
     * Funcion valida la longitud de un texto sea mayor a lo establecido
     *
     * @param texto string que se desea validar
     * @param nombre string con nombre del campo a validar, este aparecera en el
     * error si lo hay
     * @param reglas String con las reglas a aplicar:
     *
     * intger el campo a validar debe se un entero
     *
     * min:value el campo a validardebe terner un minimo de caracteres
     *
     * @throws java.lang.Exception
     */
    public static String texto(String texto, String nombre, String reglas) throws Exception {
        String[] parametros = new String[3];
        List<String> items = Arrays.asList(reglas.split("\\s*\\|\\s*"));

        for (String regla : items) {
            //parte los parametros de las reglas {nombreregla, param1, param2}
            parametros = regla.split("\\s*:\\s*");
            //verfica el nombre de la regla
            switch (parametros[0]) {
                case "min":
                    if (texto.length() < Integer.parseInt(parametros[1])) {
                        throw new Exception("El campo " + nombre + " debe tener almenos " + parametros[1] + " caracteres");
                    }
                    break;
                case "max":
                    if (texto.length() > Integer.parseInt(parametros[1])) {
                        throw new Exception("El campo " + nombre + " debe tener maximo " + parametros[1] + " caracteres");
                    }
                    break;
                case "requeried":
                    if (texto.length() == 0) {
                        throw new Exception("El campo " + nombre + " es requerido");
                    }
                    break;
                case "integer":
                    try {
                        Integer.parseInt(texto);
                    } catch (NumberFormatException | NullPointerException e) {
                        throw new Exception("El campo " + nombre + " debe ser numerico");
                    }
                    break;
                case "double":
                    break;
                case "email":
                    break;
                default:
                    break;

            }
        }
        return texto;
    }

}
