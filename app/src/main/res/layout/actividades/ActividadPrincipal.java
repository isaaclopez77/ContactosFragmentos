package com.example.borja.agendaconfragmentos.actividades;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.borja.agendaconfragmentos.R;
import com.example.borja.agendaconfragmentos.clases.Contacto;
import com.example.borja.agendaconfragmentos.fragmentos.Fragmento1;
import com.example.borja.agendaconfragmentos.fragmentos.Fragmento2;

public class ActividadPrincipal extends AppCompatActivity implements Fragmento1.OnFragmentoInteraccionListener {

    public static final String TAG = "FRAGMENTOS";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad1);


        Fragmento2 fragmento2 =(Fragmento2)getFragmentManager().findFragmentById(R.id.fragment3);

        fragmento2.onAttach(this);
    }


    @Override
    public void onFragmentoInteraccion(Contacto c) {
        Fragmento2 fragmento= (Fragmento2) getFragmentManager().findFragmentById(R.id.fragment3);
        if (fragmento == null || ! fragmento.isInLayout()) {
            Intent i = new Intent(this, ActividadSecundaria.class);
            i.putExtra("valor", c.getPrimerNumero());
            startActivity(i);
        } else {
            Intent i = new Intent(this, ActividadSecundaria.class);
            i.putExtra("valor", c.getPrimerNumero());
            startActivity(i);
        }
    }

}
