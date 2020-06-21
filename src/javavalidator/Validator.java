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

    /**
     *
     */
    public void clearErrors() {
        errores.clear();
    }

    /**
     *
     * @return
     */
    public int getCountErrors() {
        return errores.size();
    }

    /**
     *
     * @return
     */
    public List<String> getErrors() {
        return errores;
    }

    /**
     *
     * @return
     */
    public String getErrorsMessage() {
        return errores.stream().collect(Collectors.joining("\n"));
    }

    /**
     *
     * @param text
     * @param name
     * @param rules
     * @return
     */
    public String text(String text, String name, String rules) {
        return validador(text, name, rules);
    }

    /**
     *
     * @param number
     * @param name
     * @param rules
     * @return
     */
    public int integer(String number, String name, String rules) {
        rules += "|integer";
        String respuesta = validador(number, name, rules);
        return respuesta.equals("") ? 0 : Integer.parseInt(respuesta);
    }

    /**
     *
     * @param number
     * @param name
     * @param rules
     * @return
     */
    public double doble(String number, String name, String rules) {
        rules += "|double";
        String respuesta = validador(number, name, rules);
        return respuesta.equals("") ? 0 : Double.parseDouble(respuesta);
    }

    /**
     *
     * @param text
     * @param name
     * @param rules
     * @return
     */
    public String validador(String text, String name, String rules) {

        boolean isNumeric = false;
        double textoLength = text.length();

        if (textoLength > 0) {
            if (rules.contains("integer") || rules.contains("double") || rules.contains("float")) {
                try {
                    textoLength = Double.parseDouble(text);
                    isNumeric = true;
                } catch (NumberFormatException | NullPointerException e) {
                    errores.add("El campo " + name + " debe ser numerico");
                    return "";
                }
            }
        }

        String[] parameters = new String[3];
        List<String> subRules = Arrays.asList(rules.split("\\s*\\|\\s*"));
        //System.out.println(nombre + ": " + rules); //print rules
        for (String regla : subRules) {
            //parte los parametros de las reglas {nombreregla, param1, param2}
            parameters = regla.split("\\s*:\\s*");
            //System.out.println(regla + ": " + parametros[0]);
            switch (parameters[0]) {
                case "min":
                    if (textoLength < Integer.parseInt(parameters[1])) {
                        if (isNumeric) {
                            errores.add("El campo " + name + " debe ser mayor o igual a " + parameters[1]);
                        } else {
                            errores.add("El campo " + name + " debe tener almenos " + parameters[1] + " caracteres");
                        }
                    }
                    break;
                case "max":
                    if (textoLength > Integer.parseInt(parameters[1])) {
                        if (isNumeric) {
                            errores.add("El campo " + name + " debe ser menor o igual a " + parameters[1]);
                        } else {
                            errores.add("El campo " + name + " debe tener maximo " + parameters[1] + " caracteres");
                        }
                    }
                    break;
                case "integer":
                    if (textoLength > 0) {
                        try {
                            Double.parseDouble(text);
                        } catch (NumberFormatException | NullPointerException e) {
                            errores.add("El campo " + name + " debe ser numerico");
                        }
                    }
                    break;
                case "double":
                    //esta regla ya esta validada
                    break;
                case "email":
                    if (textoLength > 0) {
                        if (!validarEmail(text)) {
                            errores.add("El campo " + name + " no es un email valido");
                        }
                    }
                    break;
                case "requeried":
                    if (text.length() == 0) {
                        errores.add("El campo " + name + " es requerido");
                    }
                    break;
                default:
                    errores.add("Regla " + parameters[0] + " desconocida");
            }

        }
        return text;
    }

    /**
     *
     * @param email
     * @return
     */
    public boolean validarEmail(String email) {
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(email);
        return mather.find();
    }

}
