package com.example.borja.agendaconfragmentos.fragmentos;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.borja.agendaconfragmentos.R;
import com.example.borja.agendaconfragmentos.clases.Contacto;
import com.example.borja.agendaconfragmentos.clases.GetContactos;

import java.util.ArrayList;

/**
 * Created by marco on 17/11/2015.
 */
public class Fragmento2 extends Fragment {

    View viewFragment;
    public TextView listaTelefonos;
    private GetContactos gc;
    private CollapsingToolbarLayout collapsingToolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Intent i= getActivity().getIntent();


        viewFragment = inflater.inflate(R.layout.fragmento2, container, false);
        collapsingToolbar=(CollapsingToolbarLayout)viewFragment.findViewById(R.id.collapsingToolbar);
        collapsingToolbar.setTitle(i.getStringExtra("title"));
        listaTelefonos= (TextView) viewFragment.findViewById(R.id.listaTelefonos);
        listaTelefonos.setText(i.getStringExtra("lista"));

        return viewFragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    public String sacarNumeros(ArrayList<String> lista){
        String x="";

            for (int i=0; i<lista.size();i++){
                x=x+lista.get(i)+"\n";

            }
        return x;
    }

    public interface OnFragmentoInteraccionListener {
        void onFragmentoInteraccion(Contacto c);
    }
}
