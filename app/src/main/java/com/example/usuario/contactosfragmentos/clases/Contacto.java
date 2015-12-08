package com.example.usuario.contactosfragmentos.clases;

import java.io.Serializable;
import java.util.ArrayList;

public class Contacto implements Serializable{

    private long id;
    private String nombre;
    private ArrayList<String> numeros;

    public Contacto(String nombre, long id, ArrayList<String> numero) {
        this.nombre = nombre;
        this.id=id;
        this.numeros = numero;
    }

    public Contacto(){
        this("",0,new ArrayList<String>());
    }

    public void set(Contacto c){
        setId(c.getId());
        setNombre(c.getNombre());
        setNumeros(c.getNumeros());
    }

    public String getNombre() {
        return nombre;
    }

    public int size(){
        return numeros.size();
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
        return numeros.get(0);
    }

    public ArrayList<String> getNumeros() {
        return numeros;
    }

    public void setNumeros(ArrayList<String> numero) {
        this.numeros = numero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contacto contacto = (Contacto) o;
        if(id == contacto.id) return true;
        if (id != contacto.id) return false;
        return !(nombre != null ? !nombre.equals(contacto.nombre) : contacto.nombre != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Contacto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", numero=" + numeros +
                '}';
    }
}
