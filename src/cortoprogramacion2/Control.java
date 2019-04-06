/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cortoprogramacion2;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Ferna
 */
public class Control {

    private ArrayList<Estudiante> estudiantes;

    public Control() {
        estudiantes = new ArrayList<Estudiante>();
    }

    public void agregar(String nombre, String apellido, String grado) {
        // se le añade al arraylist un nuevo objeto del tipo estudiante pasando los parametros requeridos
        estudiantes.add(new Estudiante(nombre, apellido, verificarNumero(nombre, apellido), grado));
    }

    public String buscar(String carnet) {
        String estudiante = "No existe ese estudiante";
        for (int i = 0; i < estudiantes.size(); i++) {
            if (estudiantes.get(i).getCarnet().equals(carnet)) {
                estudiante = informacion(i);
                break;
            }
        }
        return estudiante;
    }

    // metodo que realiza el proceso de editar el registro en el arrayList y retorna su posicion que servira para editar el elemnto en la lista del formulario
    public int editar(String nombre, String apellido, String grado, String carnet) {
        boolean editado = false;
        int indice;
        for (indice = 0; indice < estudiantes.size(); indice++) {
            if (estudiantes.get(indice).getCarnet().equals(carnet)) {

                // si el parametro enviado esta vacio no se modifica 
                if (!nombre.equals("")) {
                    estudiantes.get(indice).setNombre(nombre);
                    estudiantes.get(indice).setCarnet(verificarNumero(nombre, estudiantes.get(indice).getApellido()));
                }
                if (!apellido.equals("")) {
                    estudiantes.get(indice).setApellido(apellido);
                    estudiantes.get(indice).setCarnet(verificarNumero(estudiantes.get(indice).getNombre(), apellido));
                }
                if (!grado.equals("")) {
                    estudiantes.get(indice).setGrado(grado);
                }

                editado = true;
                break;
            }
        }
        if (editado == false) {
            JOptionPane.showMessageDialog(null, "No existe ese Estudiante");
        }

        return indice;
    }

    // metodo que realiza el proceso de eliminar el registro en el arrayList y retorna su posicion que servira para eliminar el elemnto en la lista del formulario
    public int elminar(String carnet) {
        boolean borrado = false;
        int indice;

        for (indice = 0; indice < estudiantes.size(); indice++) {
            if (estudiantes.get(indice).getCarnet().equals(carnet)) {
                estudiantes.remove(indice);
                borrado = true;
                break;
            }
        }

        if (borrado == false) {
            JOptionPane.showMessageDialog(null, "No existe ese Estudiante");
        }

        return indice;
    }

    // metodo que retorna la informacion del estudiante especificado
    public String informacion(int posicion) {
        return estudiantes.get(posicion).getApellido() + " " + estudiantes.get(posicion).getNombre() + " - " + estudiantes.get(posicion).getCarnet() + " - " + estudiantes.get(posicion).getGrado();
    }

    // se verifica si ya existen carnet con las mismas iniciales (apellido - nombre) segun cuanto existan se enviara un numero que no contenga ningun carnet existente como parametro al nuevo estudiante
    public int verificarNumero(String nombre, String apellido) {
        int numero = 1;
        for (int i = 0; i < estudiantes.size(); i++) {
            if ((String.valueOf(apellido.charAt(0)) + String.valueOf(nombre.charAt(0))).equalsIgnoreCase(String.valueOf(estudiantes.get(i).getCarnet().charAt(0)) + String.valueOf(estudiantes.get(i).getCarnet().charAt(1)))) {
                numero = Integer.parseInt(String.valueOf(estudiantes.get(i).getCarnet().charAt(2)) + String.valueOf(estudiantes.get(i).getCarnet().charAt(3)) + String.valueOf(estudiantes.get(i).getCarnet().charAt(4))) + 1;
            }
        }
        return numero;
    }

    

}
