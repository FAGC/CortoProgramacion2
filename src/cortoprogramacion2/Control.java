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
        ordenarArray();
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
        ordenarArray();
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

    // metodo para ordenar el arraylist estudiantes de A - Z empezando por Apellido
    public void ordenarArray() {
        String[] arrayTemporal = new String[estudiantes.size()];
        Estudiante temp;
        int menor;
        boolean ordenado;
        int k;
        String numeroCarnet;
        for (int i = 0; i < estudiantes.size(); i++) {
            arrayTemporal[i] = informacion(i);
        }
        for (int i = 0; i < arrayTemporal.length; i++) {
            for (int j = 0; j < arrayTemporal.length - 1; j++) {
                if (arrayTemporal[j].length() < arrayTemporal[j + 1].length()) {
                    menor = arrayTemporal[j].length();
                } else {
                    menor = arrayTemporal[j + 1].length();
                }
                k = 0;
                ordenado = false;
                // se compara los caracteres necesarios para ordenar, si los caracteres en la misma posicion son iguales, se sigue iterando hasta llegar al ultimo caracter
                while (k < menor && ordenado == false) {
                    if (arrayTemporal[j].charAt(k) > arrayTemporal[j + 1].charAt(k)) {
                        numeroCarnet = estudiantes.get(j).getCarnet();
                        temp = new Estudiante(estudiantes.get(j).getNombre(), estudiantes.get(j).getApellido(), Integer.parseInt(String.valueOf(numeroCarnet.charAt(2)) + String.valueOf(numeroCarnet.charAt(3)) + String.valueOf(numeroCarnet.charAt(4))), estudiantes.get(i).getGrado());
                        estudiantes.set(j, estudiantes.get(j + 1));
                        arrayTemporal[j] = arrayTemporal[j + 1];
                        estudiantes.set(j + 1, temp);
                        ordenado = true;
                    } else if (arrayTemporal[j].charAt(k) == arrayTemporal[j + 1].charAt(k)) {
                        k++;
                    } else {
                        ordenado = true;
                    }
                }
            }
        }
    }

}
