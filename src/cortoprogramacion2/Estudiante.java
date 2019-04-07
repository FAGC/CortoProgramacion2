/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cortoprogramacion2;

import javax.swing.JOptionPane;

/**
 *
 * @author Ferna
 */
public class Estudiante {

    private String nombre;
    private String apellido;
    private String grado;
    private String Carnet;

    public Estudiante() {
    }

    public Estudiante(String nombre, String apellido, int numero, String grado) {
        setNombre(nombre);
        setApellido(apellido);
        setCarnet(numero);
        setGrado(grado);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = estruturarTexto(nombre);
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = estruturarTexto(apellido);
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public String getCarnet() {
        return Carnet;
    }

    public void setCarnet(int numero) {
        if (numero > 999) {
            JOptionPane.showMessageDialog(null, "limite de carnet");
        } else {
            //el carnet se genera con las inicial de apellido, seguida de la inicial del nombre, seguido por un numero correlativo segun cada combinacion de iniciales
            if (numero < 10) {
                this.Carnet = String.valueOf(getApellido().charAt(0)) + String.valueOf(getNombre().charAt(0)) + "00" + numero;
            } else if (numero < 100) {
                this.Carnet = String.valueOf(getApellido().charAt(0)) + String.valueOf(getNombre().charAt(0)) + "0" + numero;
            } else {
                this.Carnet = String.valueOf(getApellido().charAt(0)) + String.valueOf(getNombre().charAt(0)) + numero;
            }
        }
    }

    //Metodo que convierte a mayuscula la inial el texto y en minusculas las demas letras que se obtiene del parametro que se le pasa
    public String estruturarTexto(String campo) {
        char[] texto = new char[campo.length()];
        String newTexto = "";
        for (int i = 0; i < texto.length; i++) {
            if (i == 0) {
                texto[i] = Character.toUpperCase(campo.charAt(i));
            } else {
                texto[i] = Character.toLowerCase(campo.charAt(i));
            }
            newTexto = newTexto + texto[i];
        }
        campo = newTexto;
        return campo;
    }
}
