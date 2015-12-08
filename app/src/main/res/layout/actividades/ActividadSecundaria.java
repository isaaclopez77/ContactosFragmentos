package com.example.borja.agendaconfragmentos.actividades;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.borja.agendaconfragmentos.R;
import com.example.borja.agendaconfragmentos.fragmentos.Fragmento2;

public class ActividadSecundaria extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad1);

        Fragmento2 detalle = (Fragmento2)getFragmentManager().findFragmentById(R.id.fragment5);


    }

    public interface OnFragmentoInteraccionListener {
        public void onFragmentoInteraccion(String cadena);
    }
}