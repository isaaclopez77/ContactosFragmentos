package com.example.usuario.contactosfragmentos.fragmentos;


import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usuario.contactosfragmentos.R;
import com.example.usuario.contactosfragmentos.actividades.ActividadPrincipal;
import com.example.usuario.contactosfragmentos.clases.Contacto;
import com.example.usuario.contactosfragmentos.clases.GetContactos;
import com.example.usuario.contactosfragmentos.util.Dialogo;
import com.example.usuario.contactosfragmentos.util.OnDialogoListener;

import java.util.ArrayList;

public class Fragmento2 extends Fragment {

    View viewFragment;
    public TextView listaTelefonos, nombre;
    private GetContactos gc;
    private Button btnEditar, btnBorrar;
    public Contacto contactoSeleccionado;
    private EditText etnom, etnum1, etnum2, etnum3;
    private ArrayList<String> tlfns;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        viewFragment = inflater.inflate(R.layout.fragmento2, container, false);
        nombre = (TextView)viewFragment.findViewById(R.id.tvNombre);
        listaTelefonos= (TextView) viewFragment.findViewById(R.id.listaTelefonos);
        btnEditar = (Button)viewFragment.findViewById(R.id.btnEditar);
        btnBorrar = (Button)viewFragment.findViewById(R.id.btnBorrar);

        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(), ActividadPrincipal.class);
                i.putExtra("valor", contactoSeleccionado);
                i.putExtra("modo","borrar");
                startActivity(i);
            }
        });

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final OnDialogoListener odl = new OnDialogoListener() {
                    @Override
                    public void onPreShow(View v) {
                        etnom = (EditText)v.findViewById(R.id.tvNombreEd);
                        etnom.setText(contactoSeleccionado.getNombre());
                        etnum1 = (EditText) v.findViewById(R.id.tvNumero1Ed);
                        etnum2 = (EditText) v.findViewById(R.id.tvNumero2Ed);
                        etnum3 = (EditText) v.findViewById(R.id.tvNumero3Ed);

                        ArrayList<String> nums = contactoSeleccionado.getNumeros();
                        tlfns = new ArrayList<>();

                        switch (contactoSeleccionado.size()) {
                            case 1:
                                etnum1.setText(nums.get(0));
                                break;
                            case 2:
                                etnum1.setText(nums.get(0));
                                etnum2.setText(nums.get(1));
                                break;
                            case 3:
                                etnum1.setText(nums.get(0));
                                etnum2.setText(nums.get(1));
                                etnum3.setText(nums.get(2));
                                break;
                        }
                    }

                    @Override
                    public void onOkSelecter(View v) {
                        if(!etnum1.getText().toString().equals("")){
                            tlfns.add(etnum1.getText().toString());
                            Log.v("ESTADO", "1 NUM");
                        }

                        if(!etnum2.getText().toString().equals("")){
                            tlfns.add(etnum2.getText().toString());
                            Log.v("ESTADO", "2 NUM");
                        }
                        if(!etnum3.getText().toString().equals("")){
                            tlfns.add(etnum3.getText().toString());
                            Log.v("ESTADO", "3 NUM");
                        }

                        if(!etnom.getText().toString().equals("")){
                            contactoSeleccionado.setNombre(etnom.getText().toString().trim());
                            contactoSeleccionado.setNumeros(tlfns);

                            Intent i = new Intent(getActivity(),ActividadPrincipal.class);
                            i.putExtra("modo","editar");
                            i.putExtra("valor",contactoSeleccionado);
                            startActivity(i);
                        }else {
                            Toast.makeText(getActivity(),"El nombre es obligatorio",Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onCancelSelecter(View v) {
                        Toast.makeText(getActivity(), "Cancelado", Toast.LENGTH_SHORT).show();
                    }
                };
                Dialogo d = new Dialogo(getActivity(),R.layout.editar_contacto,odl);
                d.setTitulo("Editar contacto");
                d.show();
            }
        });
        return viewFragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void update(Contacto c) {
        contactoSeleccionado = c;
        nombre.setText(c.getNombre());
        listaTelefonos.setText("");
        for (String num : contactoSeleccionado.getNumeros()) {
            listaTelefonos.append(num + "\n");
        }
    }
}