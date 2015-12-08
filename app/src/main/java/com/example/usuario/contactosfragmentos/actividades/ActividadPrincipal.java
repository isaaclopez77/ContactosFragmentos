package com.example.usuario.contactosfragmentos.actividades;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.usuario.contactosfragmentos.R;
import com.example.usuario.contactosfragmentos.clases.Contacto;
import com.example.usuario.contactosfragmentos.fragmentos.Fragmento1;
import com.example.usuario.contactosfragmentos.fragmentos.Fragmento2;
import com.example.usuario.contactosfragmentos.util.Dialogo;
import com.example.usuario.contactosfragmentos.util.OnDialogoListener;

import java.util.ArrayList;


public class ActividadPrincipal extends AppCompatActivity{

    public static final String TAG = "FRAGMENTOS";
    private Context cx;
    private Fragmento2 fragmento2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad1);
        cx = this;
        fragmento2 =(Fragmento2)getFragmentManager().findFragmentById(R.id.fragment3);
    }
}