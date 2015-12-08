package com.example.usuario.contactosfragmentos.actividades;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.usuario.contactosfragmentos.R;
import com.example.usuario.contactosfragmentos.clases.Contacto;
import com.example.usuario.contactosfragmentos.fragmentos.Fragmento2;


public class ActividadSecundaria extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad2);

        Bundle e =getIntent().getExtras();
        Contacto c = (Contacto)e.getSerializable("valor");

        Fragmento2 detalle = (Fragmento2)getFragmentManager().findFragmentById(R.id.fragment5);
        detalle.update(c);
    }
}