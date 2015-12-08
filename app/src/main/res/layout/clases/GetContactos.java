package com.example.borja.agendaconfragmentos.clases;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import java.util.ArrayList;

/**
 * Created by borja on 01/12/2015.
 */
public class GetContactos {

    public Context ctx;

    public GetContactos(Context ctx){
        this.ctx=ctx;
    }

    public ArrayList<Contacto> getListaContactos(){
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        String proyeccion[] = null;
        String seleccion = ContactsContract.Contacts.IN_VISIBLE_GROUP + " = ? and " +
                ContactsContract.Contacts.HAS_PHONE_NUMBER + "= ?";
        String argumentos[] = new String[]{"1","1"};
        String orden = ContactsContract.Contacts.DISPLAY_NAME + " collate localized asc";
        Cursor cursor = ctx.getContentResolver().query(uri, proyeccion, seleccion, argumentos, orden);
        int indiceId = cursor.getColumnIndex(ContactsContract.Contacts._ID);
        int indiceNombre = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);

        ArrayList<Contacto> lista = new ArrayList<>();

        Contacto contacto;


        while(cursor.moveToNext()){
            contacto = new Contacto();
            contacto.setId(cursor.getLong(indiceId));
            contacto.setNombre(cursor.getString(indiceNombre));
            ArrayList<String> telefonos= getListaTelefonos(cursor.getLong(indiceId));
            contacto.setNumero(telefonos);
            lista.add(contacto);
        }
        return lista;
    }

    public ArrayList<String> getListaTelefonos(long id){
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String proyeccion[] = null;
        String seleccion = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?";
        String argumentos[] = new String[]{id+""};
        String orden = ContactsContract.CommonDataKinds.Phone.NUMBER;
        Cursor cursor = ctx.getContentResolver().query(uri, proyeccion, seleccion, argumentos, orden);
        int indiceNumero = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
        ArrayList<String> lista = new ArrayList<>();
        String numero;
        while(cursor.moveToNext()){
            numero = cursor.getString(indiceNumero);
            lista.add(limpiar_numero(numero));
        }
        return lista;
    }

    public String limpiar_numero(String x){
        char[] aux = x.toCharArray();
        for (int i =0; i<aux.length;i++){
            if (aux[i]==')'||aux[i]=='('||aux[i]=='-'){
                aux[i]=' ';
            }
        }
        x = String.valueOf(aux);
        return x.trim();
    }
}
