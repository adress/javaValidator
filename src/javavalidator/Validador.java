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
public class Validador {

    /**
     * Funcion valida la longitud de un texto sea mayor a lo establecido
     *
     * @param texto string que se desea validar
     * @param nombre string con nombre del campo a validar, este aparecera en el
     * error si lo hay
     * @param minimo int con la cantidad minima de caractes acepetados
     * @return el string en enviado si comple con la condicion
     * @throws java.lang.Exception se genera cuando el string no supera la
     * catidad minima de caractes indicados
     */
    public String texto(String texto, String nombre, int minimo) throws Exception {
        if (texto.length() < minimo) {
            if (minimo == 1) {
                throw new Exception("El campo " + nombre + " es requerido");
            } else {
                throw new Exception("El campo " + nombre + " debe tener almenos " + minimo + " caracteres");
            }
        } else {
            return texto;
        }
    }

    /**
     * Funcion valida la longitud de un texto se encuentre en un rango
     *
     * @param texto string que se desea validar
     * @param nombre string con nombre del campo a validar, este aparecera en el
     * error si lo hay
     * @param minimo int con la cantidad minima de caracteres acepetados
     * @param maximo in con la cantidad maxima de caracteres aceptados
     * @return el string en enviado si comple con la condicion
     * @throws java.lang.Exception se genera cuando el string no se encuentra en
     * el rango definido
     */
    public String texto(String texto, String nombre, int minimo, int maximo) throws Exception {

        if (texto.length() < minimo) {
            if (minimo == 1) {
                throw new Exception("El campo " + nombre + " es requerido");
            } else {
                throw new Exception("El campo " + nombre + " debe tener almenos " + minimo + " caracteres");
            }
        } else if (texto.length() > maximo) {
            throw new Exception("El campo " + nombre + " debe tener maximo " + maximo + " caracteres");
        } else {
            return texto;
        }
    }

    /**
     * Funcion valida el tamanio de un numero entero
     *
     * @param numero String numerico con el dato que se desea validar
     * @param nombre string con nombre del campo a validar, este aparecera en el
     * error si lo hay
     * @param minimo int con la cantidad minima de caractes acepetados
     * @return el numero entero si se complen las condiciones
     * @throws java.lang.Exception ocurre si el cmapo no es numerico o si no
     * supera los caracteres minimos
     */
    public int lenEntero(String numero, String nombre, int minimo) throws Exception {
        int valorNumerico = 0;
        try {
            valorNumerico = Integer.parseInt(texto(numero, nombre, minimo));
        } catch (NumberFormatException e) {
            throw new Exception("El campo " + nombre + " debe ser numerico");
        }
        return valorNumerico;
    }

    /**
     * Funcion valida el tamanio de un numero Doble
     *
     * @param numero String numerico con el dato que se desea validar
     * @param nombre String con nombre del campo a validar, este aparecera en el
     * error si lo hay
     * @param minimo int con la cantidad minima de caractes acepetados
     * @return el numero Double si se complen las condiciones
     * @throws java.lang.Exception ocurre si el camapo no es double o si no
     * supera los caracteres minimos
     */
    public double lenDoble(String numero, String nombre, int minimo) throws Exception {
        double valorNumerico = 0;
        if (minimo == 0 && numero.length() == 0) {
            return valorNumerico;
        }
        try {
            valorNumerico = Double.parseDouble(texto(numero, nombre, minimo));
        } catch (NumberFormatException e) {
            throw new Exception("El campo " + nombre + " debe ser numerico");
        }
        return valorNumerico;
    }

}
