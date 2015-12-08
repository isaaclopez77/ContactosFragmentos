package com.example.borja.agendaconfragmentos.clases;

import java.util.ArrayList;

/**
 * Created by borja on 14/10/2015.
 */
public class Contacto {
    private long id;
    private String nombre;
    private ArrayList<String> numero;

    public Contacto(String nombre, long id, ArrayList<String> numero) {
        this.nombre = nombre;
        this.id=id;
        this.numero = numero;
    }

    public Contacto(){}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getId() {
        return id;
    }

    public void setId(long apellido) {
        this.id = apellido;
    }


    public String getPrimerNumero() {
        return numero.get(0).toString();
    }

    public ArrayList<String> getNumero() {
        return numero;
    }

    public void setNumero(ArrayList<String> numero) {
        this.numero = numero;
    }

    public void agregarUnNumero(int posicion, String numero) {
        this.numero.add(posicion, numero);
    }
    public void editarNumero(int posicion, String numero){
        this.numero.set(posicion, numero);

    }

    @Override
    public String toString() {
        return "Contacto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' ;
    }
}
